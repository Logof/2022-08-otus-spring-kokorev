<template>
  <el-card v-if="loading" :bordered="false" hoverable>
    <slot name="skeleton"></slot>
  </el-card>
  <el-card v-else class="box-card">
    <template #header>
      <div class="card-header">
        <span>
          {{ props.data?.name }}
        </span>
        <div v-if="props.data?.nameAlt">
          {{ props.data?.nameAlt }}
        </div>
        <el-button-group>
          <el-button :icon="Edit" />
          <el-button :icon="Delete" />
        </el-button-group>
      </div>
    </template>
    <div>
      <p>Платформа: {{ props.data?.category.name }}</p>
      <p>Релиз: {{ props.data?.releaseType }}</p>
      <div class="bottom">
        <span></span>
        <el-button text class="button">Подробнее...</el-button>
      </div>
    </div>
  </el-card>

  <el-dialog
    :closable="false"
    :title="props.data?.name"
    :visible="visible"
    :hide-cancel="true"
    @ok="handleOk"
  >
    <a-skeleton-line>
      <a-space direction="vertical" :style="{ width: '100%' }" size="large">
        <a-skeleton-line :rows="3" />
        <a-skeleton-shape />
      </a-space>
    </a-skeleton-line>
  </el-dialog>
</template>

<script lang="ts" setup>
  import { PropType, ref } from 'vue';
  import { InfoCardRecord } from '@/api/infocard/index';
  import { Delete, Edit } from '@element-plus/icons-vue';

  const props = defineProps({
    loading: {
      type: Boolean,
      default: false,
    },
    data: {
      type: Object as PropType<InfoCardRecord>,
      default: undefined,
    },
  });

  const visible = ref(false);

  const getFullInfoCart = () => {
    visible.value = true;
  };

  const handleOk = () => {
    visible.value = false;
  };

  const createNewInfoCart = () => {};
</script>

<style scoped lang="less">
  .card-wrap {
    height: 100%;
    transition: all 0.3s;
    border: 1px solid var(--color-neutral-3);
    border-radius: 4px;
    &:hover {
      transform: translateY(-4px);
      // box-shadow: 4px 4px 10px rgba(0, 0, 0, 0.1);
    }
    :deep(.arco-card) {
      height: 100%;
      border-radius: 4px;
    }
    :deep(.arco-card-meta-title) {
      display: flex;
      align-items: center;

      // To prevent the shaking
      line-height: 28px;
    }
    :deep(.arco-skeleton-line) {
      &:last-child {
        display: flex;
        justify-content: flex-end;
        margin-top: 20px;
      }
    }
  }

  .box-card {
    margin: 10px 10px 10px 10px;
  }

  .card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
</style>
