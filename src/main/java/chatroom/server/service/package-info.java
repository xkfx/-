/**
 * 分三种service
 * 后：通用的服务，例如查询xx
 * 中：对第一种service的调用，例如计算折扣
 * 前：基于第一种第二种的复杂业务逻辑
 * 尽量不要只写一层service直接调用DAO
 */
package chatroom.server.service;