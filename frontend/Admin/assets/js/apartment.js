import { fetchApartments, fetchContracts, deleteApartment } from './api.js';

async function loadApartments() {
  const tbody = document.getElementById("apartmentTableBody");

  try {
    // Gọi API để lấy danh sách căn hộ và hợp đồng
    const [apartments, contracts] = await Promise.all([
      fetchApartments(),
      fetchContracts()
    ]);

    // Map hợp đồng theo apartmentId
    const contractMap = {};
    contracts.forEach(ct => {
      contractMap[ct.apartment?.id] = ct;
    });

    tbody.innerHTML = "";

    apartments.forEach(ap => {
      const contract = contractMap[ap.id];
      const serviceName = contract?.serviceType?.name || "Không có";
      const customerName = ap.customer?.fullName || "-";
      const customerPhone = ap.customer?.phone || "-";

      const row = document.createElement("tr");
      row.innerHTML = `
        <td>${ap.address || '-'}</td>
        <td>${serviceName}</td>
        <td>${customerName}</td>
        <td>${customerPhone}</td>
        <td>
          <button class="delete-btn" data-id="${ap.id}" title="Xoá căn hộ">
            <i class="fa-solid fa-trash-can"></i>
          </button>
        </td>
      `;
      tbody.appendChild(row);
    });

    // Gán sự kiện xóa
    document.querySelectorAll(".delete-btn").forEach(btn => {
      btn.addEventListener("click", async () => {
        const id = btn.getAttribute("data-id");
        if (confirm("Bạn có chắc muốn xoá căn hộ này?")) {
          try {
            await deleteApartment(id);
            loadApartments();
          } catch (err) {
            alert("Xoá thất bại!");
            console.error(err);
          }
        }
      });
    });

  } catch (err) {
    console.error("Lỗi khi tải danh sách căn hộ:", err);
    tbody.innerHTML = `<tr><td colspan="5">Không thể tải dữ liệu</td></tr>`;
  }
}

document.addEventListener("DOMContentLoaded", loadApartments);
