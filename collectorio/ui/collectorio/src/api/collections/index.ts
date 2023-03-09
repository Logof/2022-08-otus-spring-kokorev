import axios from 'axios';

export interface CollectionRecord {
  id: number;
  name: string;
}

export function getAllCollections() {
  return axios.get<CollectionRecord[]>(`collections`);
}

export function getCollectionById(id: number) {
  return axios.get<CollectionRecord>(`collections/${id}`);
}

export function putCollection(req: CollectionRecord) {}

export function deleteCollectionById(id: number) {}
