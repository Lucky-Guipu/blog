import moment from 'moment'
import type { MomentInput } from 'moment'

// 设置中文本地化
moment.locale('zh-cn')

/**
 * 日期格式化
 * @param value 日期源（时间戳/字符串/Date/moment对象）
 * @param format 格式化模板，如 YYYY-MM-DD HH:mm:ss
 * @returns 格式化后的日期字符串
 */
export function dateFormat(value: MomentInput, format: string): string {
  return moment(value).format(format)
}

/**
 * 相对时间（xx分钟前 / 超过30天展示完整日期）
 * @param value 日期源
 * @returns 相对时间或完整日期字符串
 */
export function dateFromNow(value: MomentInput): string {
  const target = moment(value)
  // 30天毫秒：30 * 24 * 60 * 60 * 1000 = 2592000000
  if (moment().diff(target) > 2592000000) {
    return target.format('YYYY-MM-DD HH:mm')
  }
  return target.fromNow()
}