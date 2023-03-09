<template>
  <div class="navbar">
    <div class="left-side">
      <el-space>
        <img alt="logo" src="/logo.svg" />
        <h5>{{ $t('title') }}</h5>
        <icon-menu-fold
          v-if="!topMenu && appStore.device === 'mobile'"
          style="font-size: 22px; cursor: pointer"
          @click="toggleDrawerMenu"
        />
      </el-space>
    </div>
    <ul class="right-side">
      <li>
        <el-tooltip :content="$t('settings.search')">
          <el-button class="nav-btn" type="outline" circle>
            <template #icon>
              <el-icon><Search /></el-icon>
            </template>
          </el-button>
        </el-tooltip>
      </li>
      <li>
        <el-dropdown
          ref="dropdownUser"
          trigger="contextmenu"
          style="margin-right: 30px"
        >
          <el-avatar
            class="user-avator"
            :size="30"
            :src="getAvatar"
            @click="showClick"
          />
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item>
                <el-space @click="$router.push({ name: 'UserCenter' })">
                  <el-icon><Setting /></el-icon>
                  <span>
                    {{ $t('messageBox.userCenter') }}
                  </span>
                </el-space>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-space @click="handleLogout">
                  <el-icon><Guide /></el-icon>
                  <span>
                    {{ $t('messageBox.logout') }}
                  </span>
                </el-space>
              </el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </li>
    </ul>
  </div>
</template>

<script lang="ts" setup>
  import { computed, ref, inject, reactive } from 'vue';

  import { useAppStore, useLoginStore } from '@/store';
  import { LOCALE_OPTIONS } from '@/locale';
  import useLocale from '@/hooks/locale';
  import useUser from '@/hooks/user';
  import getAvatar from '@/utils/avatar';
  import { UserFilled } from '@element-plus/icons-vue';

  import MessageBox from '../message-box/index.vue';

  const dropdownUser = ref();
  function showClick() {
    dropdownUser.value.handleOpen();
  }
  const appStore = useAppStore();
  const loginStore = useLoginStore();
  const { logout } = useUser();
  const { changeLocale, currentLocale } = useLocale();

  const locales = [...LOCALE_OPTIONS];

  const topMenu = computed(() => appStore.topMenu && appStore.menu);

  const setVisible = () => {
    appStore.updateSettings({ globalSettings: true });
  };
  const refBtn = ref();
  const triggerBtn = ref();
  const setPopoverVisible = () => {
    const event = new MouseEvent('click', {
      view: window,
      bubbles: true,
      cancelable: true,
    });
    refBtn.value.dispatchEvent(event);
  };
  const handleLogout = () => {
    logout();
  };
  const setDropDownVisible = () => {
    const event = new MouseEvent('click', {
      view: window,
      bubbles: true,
      cancelable: true,
    });
    triggerBtn.value.dispatchEvent(event);
  };

  const toggleDrawerMenu = inject('toggleDrawerMenu') as () => void;
</script>

<style scoped lang="less">
  .navbar {
    display: flex;
    justify-content: space-between;
    height: 100%;
    background-color: var(--color-bg-2);
    border-bottom: 1px solid var(--color-border);
  }

  .left-side {
    display: flex;
    align-items: center;
    padding-left: 20px;
  }

  .center-side {
    flex: 1;
  }

  .right-side {
    display: flex;
    padding-right: 20px;
    list-style: none;
    :deep(.locale-select) {
      border-radius: 20px;
    }
    li {
      display: flex;
      align-items: center;
      padding: 0 10px;
    }

    a {
      color: var(--color-text-1);
      text-decoration: none;
    }
    .nav-btn {
      border-color: rgb(var(--gray-2));
      color: rgb(var(--gray-8));
      font-size: 16px;
    }
    .trigger-btn,
    .ref-btn {
      position: absolute;
      bottom: 14px;
    }
    .trigger-btn {
      margin-left: 14px;
    }
  }

  .user-avator {
    margin-left: 20px;
  }

</style>

<style lang="less">
  .message-popover {
    .arco-popover-content {
      margin-top: 0;
    }
  }
</style>
