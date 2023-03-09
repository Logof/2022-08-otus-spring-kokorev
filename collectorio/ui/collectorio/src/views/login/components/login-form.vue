<template>
  <div class="login-form-wrapper">
    <div class="login-form-title">{{ $t('login.form.title') }}</div>
    <div class="login-form-sub-title">{{ $t('login.form.subTitle') }}</div>
    <el-form
      ref="formRef"
      :model="loginConfig"
      :rules="rules"
      label-width="0px"
      class="ms-content"
    >
      <el-form-item prop="username">
        <el-input
          v-model="form.username"
          :placeholder="$t('login.form.placeholder.username')"
        >
          <template #prepend>
            <el-button :icon="User"></el-button>
          </template>
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input
          v-model="form.password"
          type="password"
          :placeholder="$t('login.form.placeholder.password')"
        >
          <template #prepend>
            <el-button :icon="Lock"></el-button>
          </template>
        </el-input>
      </el-form-item>
      <el-space :size="16" direction="vertical">
        <div class="login-form-remember-me">
          <el-checkbox
            v-model="loginConfig.rememberMe"
            @change="setRememberMe as any"
          >
            {{ $t('login.form.rememberMe') }}
          </el-checkbox>
        </div>
        <el-button type="primary" @click="handleLogin(formRef)">
          {{ $t('login.form.login') }}
        </el-button>
      </el-space>
    </el-form>
  </div>
</template>

<script lang="ts" setup>
  import { ref, toRefs, reactive } from 'vue';
  import { Lock, User } from '@element-plus/icons-vue';
  import type { FormInstance, FormRules } from 'element-plus';
  import { ElMessage, ElNotification } from 'element-plus';
  import { LoginReq } from '@/api/auth/login';
  import { useI18n } from 'vue-i18n';
  import { useRouter } from 'vue-router';
  import { useStorage } from '@vueuse/core';
  import { useLoginStore } from '@/store';

  const loginStore = useLoginStore();
  const loading = ref(false);
  const { t } = useI18n();
  const router = useRouter();
  const formRef = ref<FormInstance>();
  const loginConfig = useStorage('login-config', {
    rememberMe: true,
    username: 'admin',
    password: '123456',
  });

  const rules: FormRules = {
    username: [
      {
        required: true,
        message: t('login.form.error.required.username'),
        trigger: 'blur',
      },
    ],
    password: [
      {
        required: true,
        message: t('login.form.error.required.password'),
        trigger: 'blur',
      },
    ],
  };

  const data = reactive({
    form: {
      username: loginConfig.value.username,
      password: loginConfig.value.password,
    } as LoginReq,
  });
  const { form } = toRefs(data);

  const handleLogin = (formEl: FormInstance | undefined) => {
    if (!formEl) return;

    formEl.validate(async (valid: boolean) => {
      if (valid) {
        try {
          loading.value = true;
          loginStore
            .login({
              username: data.form.username,
              password: data.form.password,
            })
            .then(() => {
              const { redirect, ...othersQuery } =
                router.currentRoute.value.query;
              router.push({
                name: (redirect as string) || 'Workplace',
                query: {
                  ...othersQuery,
                },
              });
              const { rememberMe } = loginConfig.value;
              loginConfig.value.username = rememberMe ? data.form.username : '';
              ElNotification({
                message: t('login.form.login.success'),
                type: 'success',
                duration: 3000,
              });
            })
            .finally(() => {
              loading.value = false;
            });
          return true;
        } catch (error) {
          ElMessage.error('Error');
          return false;
        }
      } else {
        ElMessage.error('Заполните все обязательные поля');
        return false;
      }
    });
  };

  /**
   * 记住我
   *
   * @param value 是否记住我
   */
  const setRememberMe = (value: boolean) => {
    loginConfig.value.rememberMe = value;
  };
</script>

<style lang="less" scoped>
  .login-form {
    margin-top: 32px;
    &-wrapper {
      width: 360px;
      background-color: azure;
      padding: 30px;
    }
    &-title {
      color: var(--color-text-1);
      font-weight: 500;
      font-size: 24px;
      line-height: 32px;
    }
    &-sub-title {
      color: var(--color-text-3);
      font-size: 16px;
      line-height: 24px;
    }
    &-remember-me {
      display: flex;
      justify-content: space-between;
    }
    &-register-btn {
      color: var(--color-text-3) !important;
    }
  }
</style>
