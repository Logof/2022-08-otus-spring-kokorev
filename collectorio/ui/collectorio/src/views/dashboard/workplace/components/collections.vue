<template>
  <el-card
    v-loading="loading"
    element-loading-text="Loading..."
    class="general-card"
    :header-style="{ paddingBottom: 0 }"
    :body-style="{
      paddingTop: '20px',
    }"
  >
    <template #header>
      <div class="card-header">
        <span>{{ $t('workplace.contentData') }}</span>
      </div>
    </template>
    <el-table :data="collectionList" style="width: 100%" :show-header="false" b>
      <el-table-column prop="name">
        <template #default="scope">
          <div style="display: flex; align-items: center">
            <span style="margin-left: 10px">
              <el-link :underline="false">{{ scope.row.name }}</el-link>
            </span>
          </div>
        </template>
      </el-table-column>
      <el-table-column align="right">
        <template #default="scope">
          <el-button-group class="ml-4">
            <el-button size="small" @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
            <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
          </el-button-group>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script lang="ts" setup>
  import { ref } from 'vue';
  import { CollectionRecord, getAllCollections } from '@/api/collections';
  import useLoading from '@/hooks/loading';

  const collectionList = ref<CollectionRecord[]>([]);

  const { loading, setLoading } = useLoading(true);

  const fetchData = async () => {
    setLoading(true);
    try {
      const { data: res } = await getAllCollections();
      collectionList.value = res;
    } catch (err) {
      // you can report use errorHandler or other
    } finally {
      setLoading(false);
    }
  };
  fetchData();
</script>

<style></style>
