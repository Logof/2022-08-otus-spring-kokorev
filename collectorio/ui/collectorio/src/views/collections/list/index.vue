<template>
  <div class="app-container">
    <Breadcrumb :items="['menu.category', 'menu.category.list']" />
    <a-row :gutter="20" align="stretch">
      <a-col :span="24">
        <a-card class="general-card" :title="$t('menu.category.list')">
          <a-row justify="space-between">
            <a-col :span="24">
              <a-table :data="collectionList">
                <template #columns>
                  <a-table-column
                    :title="$t('basicProfile.column.contentNumber')"
                    :width="50"
                    data-index="id"
                  />
                  <a-table-column
                    :title="$t('basicProfile.column.contentNumber')"
                  >
                    <template #cell="{ record }">
                      <a-link :href="record.id">{{
                        record.name
                      }}</a-link>
                    </template>
                  </a-table-column>
                  <a-table-column
                    :title="$t('basicProfile.column.contentNumber')"
                    :width="200"
                  >
                    <template #cell="{ record }">
                      <a-link :href="'/category/' + record.category.id">{{
                        record.category.name
                      }}</a-link>
                    </template>
                  </a-table-column>
                  <a-table-column title="Optional" :width="200">
                    <template #cell="{ record }">
                      <a-button>
                        <template #icon>
                          <icon-edit />
                        </template>
                        <template #default>{{ record.id }}</template>
                      </a-button>
                      <a-button type="primary">
                        <template #icon>
                          <icon-delete />
                        </template>
                        <template #default>{{ record.id }}</template>
                      </a-button>
                    </template>
                  </a-table-column>
                </template>
              </a-table>
            </a-col>
          </a-row>
        </a-card>
      </a-col>
    </a-row>
  </div>
</template>

<script lang="ts" setup>
  import { ref } from 'vue';
  import { CollectionRecord, getAllCollections } from '@/api/collections/index';

  const collectionList = ref<CollectionRecord[]>([]);
  const total = ref(0);
  const loading = ref(false);

  const getList = () => {
    loading.value = true;
    getAllCollections()
      .then((res) => {
        collectionList.value = res.data;
        total.value = res.data.length;
      })
      .finally(() => {
        loading.value = false;
      });
  };
  getList();
</script>
