<template>
  <el-tabs
    v-model="activeName"
    type="card"
    class="demo-tabs"
    @tab-click="handleClick"
  >
    <el-tab-pane :label="$t('cardList.tab.title.all')" name="all">
      <CardWrap
        v-for="category in categoryList"
        :id="category.id"
        :key="category.id"
        :loading="loading"
        :name="category.name"
      />
    </el-tab-pane>
    <el-tab-pane
      v-for="category in categoryList"
      :key="category.id"
      :label="category.name"
    >
      <CardWrap
        v-for="subCategory in category.subCategory"
        :id="subCategory.id"
        :key="subCategory.id"
        :loading="loading"
        :name="subCategory.name"
      />
    </el-tab-pane>
  </el-tabs>
</template>

<script lang="ts" setup>
  import { ref } from 'vue';
  import { CategoryRecord, getAllcategories } from '@/api/category/index';
  import type { TabsPaneContext } from 'element-plus';
  import CardWrap from './card-wrap.vue';

  const activeName = ref('all');

  const handleClick = (tab: TabsPaneContext, event: Event) => {
    console.log(tab, event);
  };
  const loading = ref(false);
  const categoryList = ref<CategoryRecord[]>([]);
  const total = ref(0);

  const getList = () => {
    loading.value = true;
    getAllcategories()
      .then((res) => {
        categoryList.value = res.data;
        total.value = res.data.length;
      })
      .finally(() => {
        loading.value = false;
      });
  };
  getList();
</script>

<style>
  .demo-tabs > .el-tabs__content {
    padding: 32px;
    color: #6b778c;
    font-size: 32px;
    font-weight: 600;
  }
</style>
