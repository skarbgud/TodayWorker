<template>
  <component :is="component"></component>
</template>

<script>
export default {
  name: 'BoardComponent',
  props: ['path'],
  data() {
    return {
      component: null,
    };
  },
  computed: {
    loader() {
      if (!this.path) {
        return null;
      }
      return () => import(`templates/${this.path}`);
    },
  },
  mounted() {
    this.loader()
      .then(() => {
        this.component = () => this.loader();
      })
      .catch(() => {
        this.component = () => import(`templates/default`);
      });
  },
};
</script>

<style></style>
