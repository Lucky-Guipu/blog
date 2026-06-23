import { defineStore } from 'pinia'

export const useBlogStore = defineStore('blog', {
  state: () => ({
    isBlogToHome: false,
    clientSize: {
      clientHeight: 0
    }
  })
})