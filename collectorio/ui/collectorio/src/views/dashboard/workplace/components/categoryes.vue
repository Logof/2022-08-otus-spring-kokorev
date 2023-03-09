<template>
  <el-card
    class="general-card"
    :title="$t('workplace.quick.operation')"
    :header-style="{ paddingBottom: '0' }"
    :body-style="{ padding: '24px 20px 0 20px' }"
  >
    <template #header>
      <div class="card-header">
        <span>{{ $t('workplace.contentData') }}</span>
      </div>
    </template>
    <el-tabs tab-position="right" style="height: 500px" class="demo-tabs">
      <el-tab-pane
        v-for="category in categoryList"
        :key="category.id"
        :label="category.name"
      >
        <el-scrollbar height="500px">
          <CardWrap
            v-for="subCategory in category.subCategory"
            :id="subCategory.id"
            :key="subCategory.id"
            :loading="loading"
            :name="subCategory.name"
          />
        </el-scrollbar>
      </el-tab-pane>
    </el-tabs>
  </el-card>
</template>

<script lang="ts" setup>
  import { ref } from 'vue';
  import { CategoryRecord, getAllcategories } from '@/api/category/index';
  import CardWrap from '@/views/categories/list/components/card-wrap.vue';

  const loading = ref(false);
  const categoryList = ref<CategoryRecord[]>([]);

  const getList = () => {
    loading.value = true;
    getAllcategories()
      .then((res) => {
        categoryList.value = res.data;
      })
      .finally(() => {
        loading.value = false;
      });
  };
  getList();
</script>
