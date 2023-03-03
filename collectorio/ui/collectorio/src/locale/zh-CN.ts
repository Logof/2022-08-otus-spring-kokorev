import localeWorkplace from '@/views/dashboard/workplace/locale/zh-CN';

import localeMessageBox from '@/components/message-box/locale/zh-CN';
import localeLogin from '@/views/login/locale/zh-CN';
import localeSettings from './zh-CN/settings';

export default {
  'title': 'Collectorio - каталог домашней коллекции',
  'menu.dashboard': '仪表盘',
  'menu.server.dashboard': '仪表盘-服务端',
  'menu.server.workplace': '工作台-服务端',
  'menu.system': '系统管理',
  'menu.monitor': '系统监控',
  'menu.list': '列表页',
  'menu.form': '表单页',
  'menu.profile': '详情页',
  'menu.result': '结果页',
  'menu.exception': '异常页',
  'menu.visualization': '数据可视化',
  'menu.server.monitor': '实时监控-服务端',
  'menu.arcoWebsite': 'Arco Design Vue',
  'menu.github': 'GitHub',
  'menu.user': '个人中心',
  'navbar.docs': '文档中心',
  'navbar.action.locale': '切换为中文',

  ...localeWorkplace,

  ...localeMessageBox,
  ...localeLogin,
  ...localeSettings,
};
