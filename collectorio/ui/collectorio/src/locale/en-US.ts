import localeWorkplace from '@/views/dashboard/workplace/locale/en-US';

import localeMessageBox from '@/components/message-box/locale/en-US';
import localeLogin from '@/views/login/locale/en-US';
import localeSettings from './en-US/settings';

export default {
  'title': 'Collectorio - каталог домашней коллекции',
  'menu.dashboard': 'Dashboard',
  'menu.server.dashboard': 'Dashboard-Server',
  'menu.server.workplace': 'Workplace-Server',
  'menu.system': 'System management',
  'menu.monitor': 'Monitor',
  'menu.list': 'List',
  'menu.form': 'Form',
  'menu.profile': 'Profile',
  'menu.result': 'Result',
  'menu.exception': 'Exception',
  'menu.visualization': 'Data Visualization',
  'menu.server.monitor': 'Monitor-Server',
  'menu.arcoWebsite': 'Arco Design Vue',
  'menu.github': 'GitHub',
  'menu.user': 'User Center',
  'menu.categories': 'Категории',
  'menu.collections': 'Мои коллекции',
  'menu.collectable-item': 'Предметы',
  'navbar.docs': 'Docs',
  'navbar.action.locale': 'Switch to English',

  ...localeWorkplace,

  ...localeMessageBox,
  ...localeLogin,
  ...localeSettings,
};
