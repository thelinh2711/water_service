package com.example.watersystem.service;

import com.example.watersystem.enums.InvoiceStatus;
import com.example.watersystem.model.Invoice;
import com.example.watersystem.model.Payment;
import com.example.watersystem.repository.InvoiceRepository;
import com.example.watersystem.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final InvoiceRepository invoiceRepository;

    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> getById(Long id) {
        return paymentRepository.findById(id);
    }

    public Payment save(Payment payment) {
        Payment saved = paymentRepository.save(payment);

        // Sau khi thanh toán, tính tổng tiền đã trả
        Long invoiceId = payment.getInvoice().getId();
        List<Payment> payments = paymentRepository.findByInvoiceId(invoiceId);

        BigDecimal totalPaid = payments.stream()
                .map(Payment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        invoiceRepository.findById(invoiceId).ifPresent(invoice -> {
            if (totalPaid.compareTo(invoice.getTotalAmount()) >= 0 &&
                    invoice.getStatus() != InvoiceStatus.DA_THANH_TOAN) {

                invoice.setStatus(InvoiceStatus.DA_THANH_TOAN);
                invoiceRepository.save(invoice);
            }
        });

        return saved;
    }

    public void delete(Long id) {
        paymentRepository.deleteById(id);
    }
}
