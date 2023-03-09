import { defineStore } from 'pinia';
import {
  login as userLogin,
  logout as userLogout,
  getUserInfo,
  LoginReq,
} from '@/api/auth/login';
import { setToken, clearToken } from '@/utils/auth';
import { removeRouteListener } from '@/utils/route-listener';
import { UserState } from './types';
import useAppStore from '../app';

const useLoginStore = defineStore('user', {
  state: (): UserState => ({
    userId: 0,
    username: '',
    nickname: '',
    gender: 0,
    phone: undefined,
    email: '',
    avatar: undefined,
    description: undefined,
    pwdResetTime: undefined,
    registrationDate: undefined,
    deptId: 0,
    deptName: '',

    job: 'backend',
    jobName: '后端艺术家',
    location: 'beijing',
    locationName: '北京',
    introduction: '低调星人',
    personalWebsite: 'https://blog.charles7c.top',
    role: '',
  }),

  getters: {
    userInfo(state: UserState): UserState {
      return { ...state };
    },
  },

  actions: {
    async login(req: LoginReq) {
      try {
        const res = await userLogin(req);
        setToken(res.data.token);
      } catch (err) {
        clearToken();
        throw err;
      }
    },
    async logout() {
      try {
        await userLogout();
      } finally {
        this.logoutCallBack();
      }
    },
    logoutCallBack() {
      const appStore = useAppStore();
      this.resetInfo();
      clearToken();
      removeRouteListener();
      appStore.clearServerMenu();
    },
    async getInfo() {
      const res = await getUserInfo();
      this.setInfo(res.data);
    },
    setInfo(partial: Partial<UserState>) {
      this.$patch(partial);
    },
    resetInfo() {
      this.$reset();
    },
    switchRoles() {
      return new Promise((resolve) => {
        this.role = this.role === 'user' ? 'admin' : 'user';
        resolve(this.role);
      });
    },
  },
});

export default useLoginStore;
