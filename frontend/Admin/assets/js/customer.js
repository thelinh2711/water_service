import {addCustomer, fetchCustomers, deleteCustomer } from './api.js';

async function loadCustomers() {
  const tbody = document.getElementById("customerTableBody");

  try {
    const customers = await fetchCustomers();
    tbody.innerHTML = "";

    customers.forEach(cus => {
      const row = document.createElement("tr");
      row.innerHTML = `
        <td>${cus.fullName || "-"}</td>
        <td>${cus.phone || "-"}</td>
        <td>${cus.email || "-"}</td>
        <td>${cus.username || "-"}</td>
        <td>
          <span class="status ${cus.active ? 'active' : 'inactive'}">
            ${cus.active ? 'Hoạt động' : 'Không hoạt động'}
          </span>
        </td>
        <td>${new Date(cus.createdAt).toLocaleDateString('vi-VN')}</td>
        <td>
          <button class="delete-btn" data-id="${cus.id}" title="Xoá khách hàng">
            <i class="fa-solid fa-trash-can"></i>
          </button>
        </td>
      `;
      tbody.appendChild(row);
    });

    // Gán sự kiện xoá
    document.querySelectorAll(".delete-btn").forEach(btn => {
      btn.addEventListener("click", async () => {
        const id = btn.getAttribute("data-id");
        if (confirm("Bạn có chắc muốn xoá khách hàng này?")) {
          try {
            await deleteCustomer(id);
            loadCustomers(); // Load lại danh sách
          } catch (err) {
            alert("Xoá thất bại!");
            console.error(err);
          }
        }
      });
    });
  } catch (err) {
    console.error("Lỗi khi tải danh sách khách hàng:", err);
    tbody.innerHTML = `<tr><td colspan="7">Không thể tải dữ liệu</td></tr>`;
  }
}

document.addEventListener("DOMContentLoaded", () => {
  loadCustomers();

  // Gắn modal thêm mới
  const addBtn = document.querySelector(".add-btn");
  const modal = document.getElementById("addCustomerModal");
  const form = document.getElementById("addCustomerForm");
  const cancelBtn = document.getElementById("cancelBtn");
  const closeModalBtn = document.getElementById("closeModalBtn");

  // Mở modal
  addBtn.addEventListener("click", () => {
    modal.style.display = "block";
  });

  // Đóng modal
  cancelBtn.addEventListener("click", () => {
    modal.style.display = "none";
    form.reset();
  });
  closeModalBtn.addEventListener("click", () => {
    modal.style.display = "none";
    form.reset();
  });

  // Gửi form
  form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const data = {
      fullName: document.getElementById("fullName").value,
      phone: document.getElementById("phone").value,
      email: document.getElementById("email").value,
      username: document.getElementById("username").value,
      password: document.getElementById("password").value,
    };

    try {
      const res = await addCustomer(data);
      if (!res.ok) throw new Error("Thêm khách hàng thất bại");

      alert("Thêm thành công!");
      modal.style.display = "none";
      form.reset();
      loadCustomers();
    } catch (err) {
      alert("Lỗi khi thêm khách hàng!");
      console.error(err);
    }
  });
});


