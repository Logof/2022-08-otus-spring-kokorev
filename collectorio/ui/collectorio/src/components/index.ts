import { App } from 'vue';

import Breadcrumb from './breadcrumb/index.vue';
import DateRangePicker from './date-range-picker/index.vue';
import RightToolbar from './right-toolbar/index.vue';
import SvgIcon from './svg-icon/index.vue';
import IconSelect from './icon-select/index.vue';
import download from './crud';

// Manually introduce ECharts modules to reduce packing size

export default {
  install(Vue: App) {
    // Global method mount
    Vue.config.globalProperties.download = download;

    // Global component mount
    Vue.component('Breadcrumb', Breadcrumb);
    Vue.component('DateRangePicker', DateRangePicker);
    Vue.component('RightToolbar', RightToolbar);
    Vue.component('SvgIcon', SvgIcon);
    Vue.component('IconSelect', IconSelect);
  },
};
