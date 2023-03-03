import { createApp } from 'vue';
import ElementPlus from 'element-plus';
import ArcoVue from '@arco-design/web-vue';
import ArcoVueIcon from '@arco-design/web-vue/es/icon';
import * as ElementPlusIconsVue from '@element-plus/icons-vue';
// eslint-disable-next-line import/no-unresolved
import 'virtual:svg-icons-register';
import globalComponents from '@/components';
import router from './router';
import store from './store';
import i18n from './locale';
import directive from './directive';
import App from './App.vue';
import '@arco-design/web-vue/dist/arco.css';
import 'element-plus/dist/index.css';
import '@/assets/style/global.less';
import '@/utils/request';

const app = createApp(App);

app.use(ElementPlus);
app.use(ArcoVue, {});
app.use(ArcoVueIcon);

app.use(router);
app.use(store);
app.use(i18n);
app.use(globalComponents);
app.use(directive);

app.mount('#app');
