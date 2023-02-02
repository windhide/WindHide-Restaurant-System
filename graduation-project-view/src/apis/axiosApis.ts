import axios from 'axios'
// test commit
axios.defaults.baseURL = '/api'
// axios.interceptors.request.use(
//     (config:any) => {
//             config.headers.token = localStorage.getItem('token');
//             config.headers.refreshToken = localStorage.getItem("refreshToken");
//             config.headers.Authorization = localStorage.getItem("Authorization")
//         return config;
//     },
//     error => {
//         return Promise.reject(error);
//     });
    
export default axios