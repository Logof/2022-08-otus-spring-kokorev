import axios from 'axios';
import { InfoCardRecord } from '../infocard/index';
import { CaseTypeRecord } from '../case-types/index';
import { CategoryRecord } from '../category';

export interface CollectableItemRecord {
  id: number;
  name: string;
  category: CategoryRecord;
  infoCards: InfoCardRecord[];
  caseType: CaseTypeRecord;
}

export function getAllCollectableItems() {
  return axios.get<CollectableItemRecord[]>(`collectibles`);
}

export function getAllCollectableItemsByCollectionId(collectableId: number) {
  return axios.get<CollectableItemRecord[]>(`collectibles/${collectableId}`);
}

export function getCollectableItemById(id: number) {
  return axios.get<CollectableItemRecord>(`collectibles/${id}`);
}

export function putCollectableItem(req: CollectableItemRecord) {}

export function deleteCollectableItemById(id: number) {}
