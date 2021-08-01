<template>
  <div>
    <b-container fluid>
      <b-row
        align-h="center"
        class="mt-5"
        cols="1"
        cols-sm="1"
        cols-md="1"
        cols-lg="2"
      >
        <b-col>
          <b-card-group deck>
            <b-card no-body header="인기게시글">
              <b-list-group flush>
                <b-list-group-item
                  class="d-flex justify-content-between align-items-center py-1"
                  >회사에 누가 홀터넥 입고 오면 어떨고같아?
                  <div class="d-flex align-items-center">
                    <b-icon class="mx-2" icon="hand-thumbs-up" />12<b-icon
                      class="mx-2 ml-3"
                      icon="chat"
                    />10
                  </div></b-list-group-item
                >
                <b-list-group-item
                  class="d-flex justify-content-between align-items-center py-1"
                  >친구 아들 곧 출산인데 유모차 사주고 싶은데
                  <div class="d-flex align-items-center">
                    <b-icon class="mx-2" icon="hand-thumbs-up" />23<b-icon
                      class="mx-2 ml-3"
                      icon="chat"
                    />34
                  </div></b-list-group-item
                >
                <b-list-group-item
                  class="d-flex justify-content-between align-items-center py-1"
                  >농협은행은 성과급 없나요
                  <div class="d-flex align-items-center">
                    <b-icon class="mx-2" icon="hand-thumbs-up" />18<b-icon
                      class="mx-2 ml-3"
                      icon="chat"
                    />12
                  </div></b-list-group-item
                >
              </b-list-group>
            </b-card>
          </b-card-group>
        </b-col>
      </b-row>
      <b-row
        align-h="center"
        class="mt-5"
        cols="1"
        cols-sm="1"
        cols-md="2"
        cols-lg="3"
      >
        <b-col>
          <b-card-group deck>
            <b-card border-variant="dark" header="자유게시판" align="center">
              <b-card-text>자유게시판 내용</b-card-text>
            </b-card>
          </b-card-group>
        </b-col>
        <b-col>
          <b-card-group deck>
            <b-card border-variant="dark" header="비밀게시판" align="center">
              <b-card-text>비밀게시판 내용</b-card-text>
            </b-card>
          </b-card-group>
        </b-col>
      </b-row>
      <b-row
        align-h="center"
        class="mt-5"
        cols="1"
        cols-sm="1"
        cols-md="2"
        cols-lg="3"
      >
        <b-col>
          <b-card-group deck>
            <b-card border-variant="dark" header="사는얘기" align="center">
              <b-card-text>사는얘기 내용</b-card-text>
            </b-card>
          </b-card-group>
        </b-col>
        <b-col>
          <b-card-group deck>
            <b-card border-variant="dark" header="이직, 커리어" align="center">
              <b-card-text>이직, 커리어 내용</b-card-text>
            </b-card>
          </b-card-group>
        </b-col>
      </b-row>
      <b-row
        align-h="center"
        class="mt-5"
        cols="1"
        cols-sm="1"
        cols-md="2"
        cols-lg="3"
      >
        <b-col>
          <b-card-group deck>
            <b-card border-variant="dark" header="회사생활" align="center">
              <b-card-text>회사생활 내용</b-card-text>
            </b-card>
          </b-card-group>
        </b-col>
        <b-col>
          <b-card-group deck>
            <b-card border-variant="dark" header="취미생활" align="center">
              <b-card-text>취미생활 내용</b-card-text>
            </b-card>
          </b-card-group>
        </b-col>
      </b-row>
      <b-row
        align-h="center"
        class="mt-5"
        cols="1"
        cols-sm="1"
        cols-md="2"
        cols-lg="3"
      >
        <b-col>
          <b-card-group deck>
            <b-card border-variant="dark" header="시사.이슈" align="center">
              <b-card-text>시사.이슈 내용</b-card-text>
            </b-card>
          </b-card-group>
        </b-col>
        <b-col>
          <b-card-group deck>
            <b-card border-variant="dark" header="정보게시판" align="center">
              <b-card-text>정보게시판 내용</b-card-text>
            </b-card>
          </b-card-group>
        </b-col>
      </b-row>
      <b-row
        align-h="center"
        class="mt-5"
        cols="1"
        cols-sm="1"
        cols-md="2"
        cols-lg="3"
      >
        <b-col>
          <b-card-group deck>
            <b-card border-variant="dark" header="커피타임" align="center">
              <b-card-text>커피타임 내용</b-card-text>
            </b-card>
          </b-card-group>
        </b-col>
        <b-col>
          <b-card-group deck>
            <b-card border-variant="dark" header="홍보게시판" align="center">
              <b-card-text>홍보게시판 내용</b-card-text>
            </b-card>
          </b-card-group>
        </b-col>
      </b-row>
      <b-row
        align-h="center"
        class="mt-5"
        cols="1"
        cols-sm="1"
        cols-md="2"
        cols-lg="3"
      >
        <b-col>
          <b-card-group deck>
            <b-card border-variant="dark" header="맛집노트" align="center">
              <b-card-text>맛집노트 내용</b-card-text>
            </b-card>
          </b-card-group>
        </b-col>
        <b-col>
          <b-card-group deck>
            <b-card border-variant="dark" header="중고거래" align="center">
              <b-card-text>중고거래 내용</b-card-text>
            </b-card>
          </b-card-group>
        </b-col>
      </b-row>
      <b-row
        align-h="center"
        class="mt-5 mb-5"
        cols="1"
        cols-sm="1"
        cols-md="2"
        cols-lg="3"
      >
        <b-col>
          <b-card-group deck>
            <b-card border-variant="dark" header="동호회" align="center">
              <b-card-text>동호회 내용</b-card-text>
            </b-card>
          </b-card-group>
        </b-col>
        <b-col>
          <b-card-group deck>
            <b-card border-variant="dark" header="유머" align="center">
              <b-card-text>유머 내용</b-card-text>
            </b-card>
          </b-card-group>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
export default {
  name: 'FrameSection',
  components: {},
  data() {
    return {
      items: [
        { age: 40, first_name: 'Dickerson', last_name: 'Macdonald' },
        { age: 21, first_name: 'Larsen', last_name: 'Shaw' },
        { age: 89, first_name: 'Geneva', last_name: 'Wilson' },
        { age: 38, first_name: 'Jami', last_name: 'Carney' },
      ],
    };
  },
};
</script>

<style>
.cols-sm {
  margin-top: 1;
}
</style>
