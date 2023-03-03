import axios from 'axios';
import qs from 'query-string';
import { ElNotification } from 'element-plus';
import dayjs from 'dayjs';

/**
 * 下载
 *
 * @param url URL
 * @param params 查询条件
 * @param fileName 文件名
 */
export default function download(
  url: string,
  params: any,
  fileName: string | undefined
) {
  return axios
    .get(url, {
      params,
      paramsSerializer: (obj) => {
        return qs.stringify(obj);
      },
      responseType: 'blob',
    })
    .then(async (res) => {
      // 获取文件名
      if (!fileName) {
        const contentDisposition = res.headers['content-disposition'];
        const pattern = new RegExp('filename=([^;]+\\.[^\\.;]+);*');
        const result = pattern.exec(contentDisposition) || '';
        // 对名字进行解码
        fileName = window.decodeURI(result[1]);
      } else {
        fileName = `${fileName}_${dayjs().format('YYYYMMDDHHmmss')}`;
      }

      // 创建下载的链接
      const blob = new Blob([res.data], {
        type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8',
      });
      const downloadElement = document.createElement('a');
      const href = window.URL.createObjectURL(blob);
      downloadElement.style.display = 'none';
      downloadElement.href = href;
      // 下载后文件名
      downloadElement.download = fileName;
      document.body.appendChild(downloadElement);
      // 点击下载
      downloadElement.click();
      // 下载完成，移除元素
      document.body.removeChild(downloadElement);
      // 释放掉 blob 对象
      window.URL.revokeObjectURL(href);
    })
    .catch((error) => {
      console.error(error);
      ElNotification({
        title: 'Warning',
        message:
          "If you are accessing the demo environment, clicking Export will report an error. This is because Mock.js is enabled in the demo environment, and Mock.js will set the responseType to '', which will not only lead to key judgment errors, but also cause the exported file to fail to open.",
        duration: 10000,
        type: 'warning',
      });
    });
}
