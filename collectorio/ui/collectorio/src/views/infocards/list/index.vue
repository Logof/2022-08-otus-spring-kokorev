<template>
  <div class="container">
    <div class="left-side">
      <div class="panel">
        <el-row v-for="(row, indexRow) in numRows()" :key="indexRow">
          <el-col v-for="(col, indexCol) in 3" :key="indexCol" :span="8">
            <CardWrap :data="infoCardList[indexRow * numCols + indexCol]" />
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
  import { ref } from 'vue';
  import {
    InfoCardRecord,
    getAllInfoCardInCategory,
  } from '@/api/infocard/index';
  import { useRoute } from 'vue-router';
  import CardWrap from './components/card-wrap.vue';

  const loading = ref(false);
  const infoCardList = ref<InfoCardRecord[]>([]);
  const total = ref(0);
  const route = useRoute();
  const numCols = ref(3);

  const numRows = () => {
    return Math.ceil(total.value / numCols.value);
  };

  const getList = (categoryId: number) => {
    loading.value = true;

    if (Number.isNaN(categoryId)) {
      infoCardList.value = [];
    } else {
      getAllInfoCardInCategory(categoryId)
        .then((res) => {
          infoCardList.value = res.data.sort((a, b) =>
            a.name.localeCompare(b.name)
          );
          total.value = res.data.length;
        })
        .finally(() => {
          loading.value = false;
        });
    }
  };
  getList(Number(route.params.id));
</script>

<script lang="ts">
  export default {
    name: 'InfoCardList',
  };
</script>

<style lang="less" scoped>
  .container {
    background-color: var(--color-fill-2);
    padding: 16px 20px;
    padding-bottom: 0;
    display: flex;
  }

  .left-side {
    flex: 1;
    overflow: auto;
  }

  .panel {
    background-color: var(--color-bg-2);
    border-radius: 4px;
    overflow: auto;
  }

  :deep(.panel-border) {
    margin-bottom: 0;
    border-bottom: 1px solid rgb(var(--gray-2));
  }

  .moduler-wrap {
    border-radius: 4px;
    background-color: var(--color-bg-2);
    :deep(.text) {
      font-size: 12px;
      text-align: center;
      color: rgb(var(--gray-8));
    }

    :deep(.wrapper) {
      margin-bottom: 8px;
      text-align: center;
      cursor: pointer;

      &:last-child {
        .text {
          margin-bottom: 0;
        }
      }
      &:hover {
        .icon {
          color: rgb(var(--arcoblue-6));
          background-color: #e8f3ff;
        }
        .text {
          color: rgb(var(--arcoblue-6));
        }
      }
    }

    :deep(.icon) {
      display: inline-block;
      width: 32px;
      height: 32px;
      margin-bottom: 4px;
      color: rgb(var(--dark-gray-1));
      line-height: 32px;
      font-size: 16px;
      text-align: center;
      background-color: rgb(var(--gray-1));
      border-radius: 4px;
    }
  }
</style>
