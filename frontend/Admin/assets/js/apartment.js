import { fetchApartments, fetchContracts, fetchServices, deleteApartment } from './api.js';

async function loadApartments() {
  const tbody = document.getElementById("apartmentTableBody");

  try {
    const [apartments, contracts, services] = await Promise.all([
      fetchApartments(),
      fetchContracts(),
      fetchServices()
    ]);

    // Tạo Map dịch vụ theo ID
    const serviceMap = {};
    services.forEach(s => {
      serviceMap[s.id] = s.name;
    });

    // Tạo Map hợp đồng theo apartmentId
    const contractMap = {};
    contracts.forEach(ct => {
      contractMap[ct.apartmentId] = ct;
    });

    tbody.innerHTML = "";

    apartments.forEach(ap => {
      const contract = contractMap[ap.id];
      const serviceName = contract ? serviceMap[contract.serviceTypeId] : "Không có";

      const row = document.createElement("tr");
      row.innerHTML = `
        <td>${ap.address || '-'}</td>
        <td>${serviceName}</td>
        <td>${ap.customer?.fullName || '-'}</td>
        <td>${ap.customer?.phone || '-'}</td>
        <td>
          <button class="delete-btn" data-id="${ap.id}" title="Xoá căn hộ">
            <i class="fa-solid fa-trash-can"></i>
          </button>
        </td>
      `;
      tbody.appendChild(row);
    });

    // Xử lý sự kiện xóa
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
