// api.js - chứa các hàm gọi API dùng chung cho hệ thống quản lý nước sạch

const BASE_URL = 'http://localhost:8080/api';

/**
 * Gửi POST request
 */
export async function postData(endpoint, data) {
    const response = await fetch(`${BASE_URL}${endpoint}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
    return response;
}

/**
 * Gửi GET request
 */
export async function getData(endpoint) {
    const response = await fetch(`${BASE_URL}${endpoint}`);
    if (!response.ok) {
        throw new Error(`GET ${endpoint} thất bại (${response.status})`);
    }
    return await response.json();
}

/**
 * Gửi DELETE request
 */
export async function deleteData(endpoint) {
    const response = await fetch(`${BASE_URL}${endpoint}`, {
        method: 'DELETE'
    });

    if (!response.ok) {
        throw new Error(`DELETE ${endpoint} thất bại (${response.status})`);
    }

    return true; // hoặc return response nếu cần
}

/** ==================== API cụ thể ==================== */

// Đăng nhập admin
export async function loginAdmin(data) {
    const response = await postData('/admin-users/login', data);

    if (!response.ok) {
        const errorText = await response.text();
        throw new Error(errorText); // ⛔️ đã throw lỗi rồi
    }

    return await response.json(); // ✅ trả JSON khi thành công
}

// Lấy danh sách khách hàng
export function fetchCustomers() {
    return getData('/customers');
}

// Thêm mới khách hàng
export function addCustomer(data) {
    return postData('/customers', data);
}

// Xoá khách hàng theo ID
export function deleteCustomer(id) {
    return deleteData(`/customers/${id}`);
}

export function fetchApartments() {
    return getData('/apartments');
}

export function deleteApartment(id) {
    return deleteData(`/apartments/${id}`);
  }

// Lấy danh sách dịch vụ nước
export function fetchServices() {
    return getData('/water-service-types');
}

// Lấy danh sách hợp đồngđồng
export function fetchContracts() {
    return getData('/contracts');
}

// Lấy danh sách bảng giá
export function fetchTieredPrices() {
    return getData('/tiered-prices');
}

// Lấy danh sách hóa đơn
export function fetchInvoices() {
    return getData('/invoices');
}

// Lấy danh sách thống kê
export function fetchStatsByMonth() {
    return getData('/stats/monthly');
}
