const config = {
  baseUrl: 'localhost:8081/liveboard/'
}

async function getLiveBoardList() {
  try {
    const response = await axios.get(`${config.baseUrl}get-live-board-list.do`);
    return response;
  } catch (error) {
    console.log(error);
  }
}

export {
  getLiveBoardList,
}