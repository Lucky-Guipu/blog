import axios from '@/plugins/axios'

export function getBlogList(pageNum: number) {
    return axios({
        url: 'blogs',
        method: 'GET',
        params: {
            pageNum
        }
    })
}

export function getCategoryBlogList(categoryName: string, pageNum: number) {
    return axios({
        url: 'category',
        method: 'GET',
        params: {
            categoryName,
            pageNum
        }
    })
}

export function getTagBlogList(tagName: string, pageNum: number) {
    return axios({
        url: 'tag',
        method: 'GET',
        params: {
            tagName,
            pageNum
        }
    })
}

export function getBlogById(id: number) {
    return axios({
        url: 'blog',
        method: 'GET',
        params: {
            id
        }
    })
}