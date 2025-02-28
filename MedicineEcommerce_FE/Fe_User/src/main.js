import '@/services/axios/interceptors';
import { createPinia } from 'pinia';
import { createApp } from 'vue';
import App from './App.vue';
import { i18n } from './locales';
import router from './router';

import BlockViewer from '@/components/BlockViewer.vue';
import Aura from '@primevue/themes/aura';
import PrimeVue from 'primevue/config';
import ConfirmationService from 'primevue/confirmationservice';
import ToastService from 'primevue/toastservice';

import '@/assets/styles.scss';
import '@/assets/tailwind.css';

import primeVueLocale from '@/locales/primevue/vi';
import { definePreset } from '@primevue/themes';
import { getPresetExt } from './constants/theme';

const app = createApp(App);
const pinia = createPinia();
const myPreset = definePreset(Aura, getPresetExt());

app.use(router);
app.use(pinia);
app.use(i18n);
app.use(PrimeVue, {
  theme: {
    preset: myPreset,
    options: {
      darkModeSelector: '.app-dark'
    }
  },
  locale: primeVueLocale
});
app.use(ToastService);
app.use(ConfirmationService);

app.component('BlockViewer', BlockViewer);

app.mount('#app');
