import { loginAdmin } from './api.js';

document.getElementById('loginForm').addEventListener('submit', async function (e) {
    e.preventDefault();

    const username = document.getElementById('username').value.trim();
    const password = document.getElementById('password').value.trim();
    const errorMessage = document.getElementById('errorMessage');

    try {
        const data = await loginAdmin({ username, password }); // ✅ nếu không throw -> thành công

        // ✅ Ví dụ lưu tên người dùng
        localStorage.setItem('customerName', data.fullName);
        // ✅ Chuyển trang
        window.location.href = './customer.html';
    } catch (error) {
        console.error('Lỗi khi đăng nhập:', error.message);
        errorMessage.textContent = 'Tên đăng nhập hoặc mật khẩu không đúng.';
    }
});