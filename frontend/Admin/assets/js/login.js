import { loginAdmin } from './api.js';

document.getElementById('loginForm').addEventListener('submit', async function (e) {
    e.preventDefault();

    const username = document.getElementById('username').value.trim();
    const password = document.getElementById('password').value.trim();
    const errorMessage = document.getElementById('errorMessage');

    try {
        const response = await loginAdmin({ username, password });

        if (response.ok) {
            window.location.href = './customer.html';
        } else {
            errorMessage.textContent = 'Tên đăng nhập hoặc mật khẩu không đúng.';
        }
    } catch (error) {
        console.error('Lỗi khi đăng nhập:', error);
        errorMessage.textContent = 'Không thể kết nối đến máy chủ.';
    }
});