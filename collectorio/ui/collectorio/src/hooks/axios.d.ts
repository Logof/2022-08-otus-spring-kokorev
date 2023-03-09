import axios, { Axios, AxiosResponse, AxiosRequestConfig } from 'axios';

declare module 'axios' {
  interface AxiosResponse<T = any> {
    status: string;
    code: number;
    message: string; // 状态信息
    data: T;
  }
  export function create(config?: AxiosRequestConfig): AxiosInstance;
}
