import { useRouter } from 'vue-router';
import { useI18n } from 'vue-i18n';
import { ElMessage } from 'element-plus';

import { useLoginStore } from '@/store';

export default function useUser() {
  const { t } = useI18n();
  const router = useRouter();
  const loginStore = useLoginStore();
  const logout = async (logoutTo?: string) => {
    await loginStore.logout();
    const currentRoute = router.currentRoute.value;

    ElMessage.success(t('login.form.logout.success'));
    router.push({
      name: logoutTo && typeof logoutTo === 'string' ? logoutTo : 'login',
      query: {
        ...router.currentRoute.value.query,
        redirect: currentRoute.name as string,
      },
    });
  };
  return {
    logout,
  };
}
