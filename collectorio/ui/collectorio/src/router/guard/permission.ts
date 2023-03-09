import type { Router, RouteRecordNormalized } from 'vue-router';
import NProgress from 'nprogress'; // progress bar

import usePermission from '@/hooks/permission';
import { useLoginStore, useAppStore } from '@/store';
import { appRoutes } from '../routes';
import { NOT_FOUND } from '../constants';

export default function setupPermissionGuard(router: Router) {
  router.beforeEach(async (to, from, next) => {
    const loginStore = useLoginStore();
    const Permission = usePermission();
    const permissionsAllow = Permission.accessRouter(to);

    // eslint-disable-next-line no-lonely-if
    if (permissionsAllow) next();
    else {
      const destination =
        Permission.findFirstPermissionRoute(appRoutes, loginStore.role) ||
        NOT_FOUND;
      next(destination);
    }

    NProgress.done();
  });
}
