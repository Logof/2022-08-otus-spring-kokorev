<template>
  <el-form :model="form" label-width="120px">
    <el-form-item label="Название">
      <el-input v-model="form.name" />
    </el-form-item>
    <el-form-item label="Корпус">
      <el-select
        v-model="form.caseType"
        filterable
        reserve-keyword
        :loading="loading"
      >
        <el-option
          v-for="item in options"
          :key="item.id"
          :label="item.name"
          :value="item.id"
        />
      </el-select>
    </el-form-item>
    <el-form-item label="Содержание">
      <el-select
        v-model="form.infoCards"
        filterable
        reserve-keyword
        placeholder="Please enter a keyword"
        :loading="loading"
      >
      </el-select>
    </el-form-item>
  </el-form>
</template>

<script setup lang="ts">
  import { ref, reactive, onMounted } from 'vue';
  import { CollectableItemRecord } from '@/api/collectable-item/index';
  import {
    CaseTypeRecord,
    getCaseTypeListByCategory,
  } from '@/api/case-types/index';

  const options = ref<CaseTypeRecord[]>([]);
  const fullCaseTypeList = ref<CaseTypeRecord[]>([]);

  const props = defineProps({
    loading: {
      type: Boolean,
      default: false,
    },
    object: {
      type: Object as () => CollectableItemRecord,
      required: true,
      default: () => ({} as CollectableItemRecord),
    },
  });

  const loading = ref(props.loading);

  const form = reactive({
    name: props.object.name,
    caseType: props.object.caseType,
    infoCards: props.object.infoCards,
  });

  onMounted(() => {
    loading.value = true;
    getCaseTypeListByCategory(props.object.category.id)
      .then((res) => {
        fullCaseTypeList.value = res.data;
      })
      .finally(() => {
        loading.value = false;
      });
  });

  const remoteMethod = () => {
    options.value = fullCaseTypeList.value;
  };
</script>
