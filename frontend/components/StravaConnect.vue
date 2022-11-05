<template>
  <a href="https://www.strava.com/oauth/authorize?client_id=94826&redirect_uri=http://localhost:3000&response_type=code&approval_prompt=auto&scope=read,read_all,activity:read">Connect
    to Strava</a>
</template>

<script>
export default {
  name: "StravaConnect",
  data: () => ({
    text: ''
  }),
  async fetch() {
    const code = this.$route.query.code;
    const scope = this.$route.query.scope;
    if (code && scope) {
      console.log(code);
      console.log(scope);
      // Log-in with Strava
      const res = await this.$http.$get(`http://localhost:8080/strava/connect?code=${code}&scope=${scope}`)
      console.log(res)
      // Get routes
      const routes = await this.$http.$get(`http://localhost:8080/strava/athlete/${res.athleteId}/routes`)
      console.log(routes)
    }
  }
}
</script>

<style scoped>

</style>