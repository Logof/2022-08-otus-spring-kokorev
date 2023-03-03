<template>
  <div class="container">
    <div class="left-side">
      <div class="panel">
        <el-table
          :data="collectableList"
          style="width: 100%; margin-bottom: 20px"
          row-key="id"
          border
          :tree-props="{
            children: 'infoCards',
            hasChildren: 'infoCards.length',
          }"
        >
          <el-table-column prop="name" label="Title" sortable />
          <el-table-column
            prop="category.name"
            label="Name"
            sortable
            width="180px"
          />
          <el-table-column
            prop="caseType.id"
            label="Сase Type"
            sortable
            width="80px"
          />
          <el-table-column
            prop="caseType.name"
            label="Сase Type"
            sortable
            width="180px"
          />
          <el-table-column
            prop="condition"
            label="Сondition"
            sortable
            width="180px"
          />
          <el-table-column align="right" width="140px">
            <template #default="scope">
              <el-button-group class="ml-4">
                <el-button
                  v-if="scope.row.infoCards"
                  size="small"
                  @click="
                    dialogEditVisible = true;
                    handleEdit(scope.$index, scope.row);
                  "
                  >Edit</el-button
                >
                <el-button
                  v-if="scope.row.infoCards"
                  size="small"
                  type="danger"
                  @click="handleDelete(scope.$index, scope.row)"
                  >Delete</el-button
                >
              </el-button-group>
            </template>
          </el-table-column>
        </el-table>
        <el-dialog v-model="dialogEditVisible" :title="collectable?.name">
          <EditForm :object="collectable" />
        </el-dialog>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
  import { ref } from 'vue';
  import { useRoute } from 'vue-router';
  import {
    CollectableItemRecord,
    getAllCollectableItems,
    getAllCollectableItemsByCollectionId,
  } from '@/api/collectable-item/index';
  import EditForm from '../components/edit-form.vue';

  const route = useRoute();
  const collectableList = ref<CollectableItemRecord[]>([]);
  const collectable = ref<CollectableItemRecord>();
  const total = ref(0);
  const loading = ref(false);
  const dialogEditVisible = ref(false);

  const handleEdit = (index: number, row: CollectableItemRecord) => {
    collectable.value = row;
  };

  const handleDelete = (index: number, row: CollectableItemRecord) => {
    console.log(index, row);
  };

  const getList = () => {
    loading.value = true;

    if (
      route.params.id === undefined ||
      Number.isNaN(Number(route.params.id))
    ) {
      getAllCollectableItems()
        .then((res) => {
          collectableList.value = res.data;
          total.value = res.data.length;
        })
        .finally(() => {
          loading.value = false;
        });
    } else {
      getAllCollectableItemsByCollectionId(Number(route.params.id))
        .then((res) => {
          collectableList.value = res.data;
          total.value = res.data.length;
        })
        .finally(() => {
          loading.value = false;
        });
    }
  };
  getList();
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

  .right-side {
    width: 30%;
    margin-left: 16px;
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

<style lang="less" scoped>
  // responsive
  .mobile {
    .container {
      display: block;
    }
    .right-side {
      // display: none;
      width: 100%;
      margin-left: 0;
      margin-top: 16px;
    }
  }
</style>
