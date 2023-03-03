import axios from 'axios';
import type { AxiosRequestConfig, AxiosResponse } from 'axios';

import { getToken } from '@/utils/auth';
import { ElMessage } from 'element-plus';

// default config
if (import.meta.env.VITE_API_BASE_URL) {
  axios.defaults.baseURL = import.meta.env.VITE_API_BASE_URL;
  axios.defaults.timeout = 60000; // 1 минута
}

// request interceptors
axios.interceptors.request.use(
  (config: AxiosRequestConfig) => {
    const token = getToken();
    if (token) {
      if (!config.headers) {
        config.headers = {};
      }
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export interface HttpResponse<T = unknown> {
  code: number; // 状态码
  data: T; // 返回数据
  status: string; // 是否成功
  message: string; // 状态信息
}

// response interceptors
axios.interceptors.response.use(
  (response: AxiosResponse<HttpResponse>) => {
    // 二进制数据则直接返回
    if (
      response.request.responseType === 'blob' ||
      response.request.responseType === 'arraybuffer'
    ) {
      return response;
    }

    if (response.request.responseURL.endsWith('api/auth/logout')) {
      return response;
    }
    // 操作成功则直接返回
    const res = response.data;
    if (res.code === 0) {
      return res;
    }
    // 操作失败，弹出错误提示
    ElMessage({
      message: res.message,
      duration: 3000,
      type: 'error',
    });
    //
    // if (res.code === 401) {
    //   // 重定向路由到登录页面
    // }
    return Promise.reject(new Error(res.message));
  },
  (error) => {
    const res = error.response.data;
    ElMessage({
      message: res.message || 'Network Error',
      duration: 3000,
      type: 'error',
    });
    return Promise.reject(error);
  }
);
