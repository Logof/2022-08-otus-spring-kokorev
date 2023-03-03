import { DEFAULT_LAYOUT } from '../base';
import { AppRouteRecordRaw } from '../types';

const CATEGORIES: AppRouteRecordRaw = {
  path: '/categories',
  name: 'categories',
  component: DEFAULT_LAYOUT,
  redirect: '/categories/list',
  meta: {
    locale: 'menu.categories',
    icon: 'icon-settings',
    requiresAuth: true,
    order: 1,
    hideChildrenInMenu: true,
  },
  children: [
    {
      path: ':id(\\d+)?',
      name: 'info_card_in_categories',
      component: () => import('@/views/infocards/list/index.vue'),
      meta: {
        requiresAuth: true,
        roles: ['*'],
        activeMenu: 'categories',
      },
    },
    {
      path: 'list',
      name: 'categories_list',
      component: () => import('@/views/categories/list/index.vue'),
      meta: {
        requiresAuth: true,
        roles: ['*'],
        activeMenu: 'categories',
      },
    },
  ],
};

export default CATEGORIES;
