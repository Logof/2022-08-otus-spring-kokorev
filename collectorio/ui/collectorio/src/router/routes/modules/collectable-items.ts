import { DEFAULT_LAYOUT } from '../base';
import { AppRouteRecordRaw } from '../types';

const COLLECTABLES: AppRouteRecordRaw = {
  path: '/collectables',
  name: 'collectables',
  component: DEFAULT_LAYOUT,
  redirect: '/collectables/list',
  meta: {
    locale: 'menu.collectable-item',
    icon: 'icon-settings',
    requiresAuth: true,
    order: 1,
    hideChildrenInMenu: true,
  },
  children: [
    {
      path: 'list',
      name: 'list',
      component: () => import('@/views/collectable-item/list/index.vue'),
      meta: {
        locale: 'menu.collectable-item.list',
        requiresAuth: true,
        roles: ['*'],
        activeMenu: 'collectables',
      },
    },
    {
      path: ':id',
      name: 'collectables',
      component: () => import('@/views/collectable-item/list/index.vue'),
      meta: {
        locale: 'menu.collectables.list',
        requiresAuth: true,
        roles: ['*'],
        activeMenu: 'collectables',
      },
    },
  ],
};

export default COLLECTABLES;
