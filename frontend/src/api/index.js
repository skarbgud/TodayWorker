import axios from 'axios';

const axiosService = axios.create({
  baseURL: process.env.VUE_APP_PUBLIC_PATH
});

export {
  axiosService,
};