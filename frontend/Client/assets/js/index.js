import { registerCustomer, loginCustomer } from './api-client.js';

const loginForm = document.getElementById("login-form");
const registerForm = document.getElementById("register-form");

// Gắn hàm vào window để gọi từ HTML onclick
window.showForm = function (formType) {
  if (formType === 'login') {
    loginForm.style.display = 'flex';
    registerForm.style.display = 'none';
  } else {
    loginForm.style.display = 'none';
    registerForm.style.display = 'flex';
  }
};

document.addEventListener("DOMContentLoaded", () => {
  const registerBtn = document.getElementById("register-btn");

  registerBtn.addEventListener("click", async () => {
    const fullName = document.getElementById("register-fullname").value.trim();
    const phone = document.getElementById("register-phone").value.trim();
    const email = document.getElementById("register-email").value.trim();
    const username = document.getElementById("register-username").value.trim();
    const password = document.getElementById("register-password").value.trim();

    if (!fullName || !phone || !email || !username || !password) {
      alert("Vui lòng nhập đầy đủ thông tin.");
      return;
    }

    try {
      const response = await registerCustomer({
        fullName,
        phone,
        email,
        username,
        password
      });

      if (response.ok) {
        alert("Đăng ký thành công!");
        showForm('login');
      } else if (response.status === 400) {
        alert("Tên đăng nhập đã tồn tại.");
      } else {
        alert("Đăng ký thất bại.");
      }
    } catch (err) {
      console.error(err);
      alert("Lỗi khi kết nối đến server.");
    }
  });
});

document.getElementById("login-btn").addEventListener("click", async () => {
    const username = document.getElementById("login-username").value.trim();
    const password = document.getElementById("login-password").value.trim();

    if (!username || !password) {
        alert("Vui lòng nhập tên đăng nhập và mật khẩu");
        return;
    }

    try {
        const result = await loginCustomer({ username, password });
        alert("Đăng nhập thành công, xin chào " + result.fullName);

        // (Tuỳ chọn) Lưu token vào localStorage để sử dụng sau
        // localStorage.setItem('token', result.token);
        // window.location.href = "dashboard.html";
    } catch (error) {
        alert("Đăng nhập thất bại: " + error.message);
    }
});