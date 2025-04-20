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
 * Gửi PUT request
 */
export async function putData(endpoint, data) {
    const response = await fetch(`${BASE_URL}${endpoint}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
    return response;
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
    return true;
}

/** ==================== API dành cho CLIENT ==================== */

// Đăng ký người dùng
export function registerCustomer(data) {
    return postData('/customers', data);
}

// Đăng nhập client
export async function loginCustomer(data) {
    const response = await postData('/customers/login', data);

    if (!response.ok) {
        const errorText = await response.text();
        throw new Error(errorText);
    }

    return await response.json();
}
