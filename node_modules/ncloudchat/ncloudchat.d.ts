declare module 'cloudchat/CloudChat' {
  /**
   * Copyright (c) NBASE CORP. and its affiliates.
   *
   * This source code is licensed under the MIT license found in the
   * LICENSE file in the root directory of this source tree.
   */
  import type { ChannelInput, MarkInput, PinInput } from "cloudchat/Type";
  /**
   * Class NCloudChat holds all the NCloudChat functionalities.
   *
   * @class
   */
  export default class NCloudChat {
      private dp;
      private socket;
      private connected;
      private connectedCount;
      /**
       * Create a `NCloudChat` instance and a Dispatcher.
       *
       * @constructs
       */
      constructor(debug?: boolean);
      clear(): void;
      /**
       * Initialize a new `NCloudChat` instance of project and user.
       *
       * @function initialize
       * @param {string} projectId - The id of a project.
       */
      initialize(projectId: string): void;
      getLang(): any;
      setLang(lang: string): void;
      getUser(): any;
      getProjectId(): any;
      setServerUrl(url: string): void;
      setSocketUrl(url: string): void;
      setProjectId(projectId: string): void;
      setToken(token: string): void;
      getToken(): any;
      setUser(user: any): void;
      /**
       * Decodes a base-64 encoded string.
       *
       * @function ObjectId
       * @param {string} id - An encoded string to be decoded.
       * @returns {string} decoded string.
       */
      ObjectId(id: string): string;
      private handleBase64Ids;
      private inputValidation;
      /**
       * Create a connection between a socket and a client.
       *
       * @async connect
       * @param {any} user
       * @param {string} userToken
       * @returns
       */
      connect(user: any, userToken?: string): Promise<any>;
      /**
       * update user
       * @async
       * @function updateUser
       * @param {any} update
       * @returns {Promise<any>}
       */
      updateUser(update: any): Promise<any>;
      /**
       * Mute channel notifications.
       *
       * @async
       * @function mute
       * @param {string} channel
       * @returns {Promise<any>}
       */
      mute(channel: string): Promise<any>;
      /**
       * Unmute channel notifications.
       *
       * @async
       * @function unmute
       * @param {string} channel
       * @returns {Promise<any>}
       */
      unmute(channel: string): Promise<any>;
      /**
       * Subscribe a channel.
       *
       * @async
       * @function subscibe
       * @param {string} channel - The id of a channel.
       * @param {any} option
       * @returns
       */
      subscribe(channel: string, option?: any): Promise<any>;
      /**
       * Unsubscribe from a channel.
       *
       * @async
       * @function unsubscribe
       * @param {string} channel - The id of a chennel
       * @returns {Promise<any>}
       */
      unsubscribe(channel: string): Promise<any>;
      /**
       * Translate a message from a souce language to a target language.
       *
       * @async
       * @function translateMessage
       * @param {string} channelId - The id of a channel.
       * @param {string} source - A source language.
       * @param {string} target - A target language.
       * @param {string} message - An message to be translated.
       * @returns {Promise<any>}
       */
      translateMessage(channelId: string, source: string, target: string, message: string): Promise<any>;
      /**
       * Send a message to a channel.
       *
       * @async
       * @function sendMessage
       * @param {string} channel - The id of a channel.
       * @param {any} opt
       * @returns {Promise<any>}
       */
      sendMessage(channel: string, opt: any): Promise<any>;
      /**
       * Send an express message to a channel.
       *
       * @async
       * @function sendExpressMessage
       * @param {string} channel - The id of a channel.
       * @param {any} opt
       * @returns {Promise<any>}
       */
      sendExpressMessage(channel: string, opt: any): Promise<any>;
      /**
       * Send an image to a channel. Only supports png, jpg, jpeg file types.
       *
       * @async
       * @function sendImage
       * @param {string} channelId - The id of a channel.
       * @param {any} file - An image file.
       * @returns {Promise<any>}
       */
      sendImage(channelId: string, file: any): Promise<any>;
      /**
       * Update a message.
       *
       * @todo
       * @async
       * @function updateMessage
       * @param {string} channel
       * @param {string} message_id
       * @param {string} message
       */
      updateMessage(channel: string, message_id: string, message: string): Promise<void>;
      /**
       * Delete a message.
       * @async
       * @function deleteMessage
       * @param {string} channel
       * @param {string} message_id
       */
      deleteMessage(channel: string, message_id: string): Promise<any>;
      /**
       * Disconnect from a socket.
       *
       * @async
       * @function disconnect
       * @returns
       */
      disconnect(): Promise<void>;
      /**
       * Check if a client is connected to a socket.
       *
       * @function isConnected
       * @returns {boolean}
       */
      isConnected(): boolean;
      isSocketConnected(): boolean;
      /**
       * Bind fn event handlers.
       *
       * @async
       * @function bind
       * @param {string} id
       * @param {any} fn
       */
      bind(id: string, fn: any): Promise<void>;
      /**
       * Unbind fn event handlers.
       *
       * @async
       * @function unbind
       * @param {string} id
       * @param {any} fn
       */
      unbind(id: string): Promise<void>;
      /**
       * Unbind all event handlers.
       * @param id
       */
      unbindall(id: string): Promise<void>;
      /**
       * Get current friends list of all status.
       *
       * @async
       * @function getFriendships
       * @param {string} filter - Field value for filter.
       * @param {string} sort - Field value for sorting.
       * @param {string} option - Optional option: ex) offset, per_page.
       * @returns {Promise<any>}
       */
      getFriendships(filter: any, sort?: any, option?: any): Promise<any>;
      /**
       * Get member blocks.
       *
       * @async
       * @function getBannedUsers
       * @param {string} filter - Field value for filter.
       * @param {string} sort - Field value for sorting.
       * @param {string} option - Optional option: ex) offset, per_page.
       * @returns {Promise<any>}
       */
      getBannedUsers(filter: any, sort?: any, option?: any): Promise<any>;
      /**
       * Get information data of the member.
       *
       * @async
       * @function getUsers
       * @param {string} filter - Field value for filter.
       * @param {string} sort - Field value for sorting.
       * @param {string} option - Optional option: ex) offset, per_page.
       * @returns {Promise<any>}
       */
      getUsers(filter: any, sort?: any, option?: any): Promise<any>;
      /**
       * Request a friendship to a user.
       *
       * @async
       * @function requestFriend
       * @param {string} friendId - An id of the friend to request.
       * @returns {Promise<any>}
       */
      requestFriend(friendId: string): Promise<any>;
      /**
       * Accept a friends request.
       *
       * @async
       * @function acceptFriend
       * @param {string} friendId - The id of the friend to accept the request.
       * @returns {Promise<any>}
       */
      acceptFriend(friendId: string): Promise<any>;
      /**
       * Reject a friend request.
       *
       * @async
       * @function rejectFriend
       * @param {string} friendId - An id of the friend to be rejected.
       * @returns {Promise<any>}
       */
      rejectFriend(friendId: string): Promise<any>;
      /**
       * Remove a friend.
       *
       * @async
       * @function removeFriend
       * @param {string} friendId - An id of the friend to be removed.
       * @returns {Promise<any>}
       */
      removeFriend(friendId: string): Promise<any>;
      /**
       * Get a count of unread messages of a channel.
       *
       * @async
       * @function countUnread
       * @param {string} channelId - The id of a channel.
       * @returns {Promise<any>}
       */
      countUnread(channelId: string): Promise<any>;
      /**
       * Get subscription data of the user.
       *
       * @async
       * @function getSubscription
       * @param {string} channelId - The id of a channel.
       * @param {string} id
       * @returns {Promise<any>}
       */
      getSubscription(channelId: string, id: string): Promise<any>;
      /**
       * Get data of a single channel from the endpoint.
       *
       * @async
       * @function getChannel
       * @param {string} channelId - An id of the channel.
       * @returns {Promise<any>} The data of the channel.
       */
      getChannel(channelId: string): Promise<any>;
      /**
       * Get data of multiple channels.
       *
       * @async
       * @function getChannels
       * @param {string} filter - Field value for filter.
       * @param {string} sort - Field value for sorting.
       * @param {string} option - Optional option: ex) offset, per_page.
       * @returns {Promise<any[]>} An array of data of all channels.
       */
      getChannels(filter: any, sort?: any, option?: any): Promise<any>;
      /**
       * @async
       * @function getMessage
       * @param {string} channelId - The id of a channel.
       * @param {string} messageId - The id of a message.
       * @returns {Promise<any>}
       */
      getMessage(channelId: string, messageId: string): Promise<any>;
      /**
       * Get data of the all messages.
       *
       * @async
       * @function getMessages
       * @param {string} filter - Field value for filter.
       * @param {string} sort - Field value for sorting.
       * @param {string} option - Optional option: ex) offset, per_page.
       * @returns {Promise<any>}
       */
      getMessages(filter: any, sort: any, option: any): Promise<any>;
      /**
       * Get a list of subscibed member of the channel.
       * @param {string} filter  - Field value for filter.
       * @param {string} sort - Field value for sorting.
       * @param {string} option - Optional option: ex) offset, per_page.
       * @returns {Promise<any>}
       */
      getSubscriptions(filter: any, sort: any, option: any): Promise<any>;
      /**
       * Create a new channel.
       *
       * @async
       * @function createChannel
       * @param {ChannelInput} channel - Configuration options of the new channel.
       * @returns {Promise<any>} The data of the newly created channel.
       */
      createChannel(channel: ChannelInput): Promise<any>;
      /**
       * Update channel options.
       *
       * @async
       * @function updateChannel
       * @param {string} channelId - An id of the channel.
       * @param {ChannelInput} channel - New options of the channel.
       * @returns {Promise<any>} The data of the updated channel.
       */
      updateChannel(channelId: string, channel: ChannelInput): Promise<any>;
      /**
       * Delete a channel.
       *
       * @async
       * @function deleteChannel
       * @param {string} channelId - An channel id.
       * @returns {Promise<any>}
       */
      deleteChannel(channelId: string): Promise<any>;
      /**
       * Update a subscription information, such as marking message read.
       *
       * @async
       * @function markRead
       * @param {string} channelId - The id of a channel.
       * @param {MarkInput} mark
       * @param {string} option
       * @returns {Promise<any>}
       */
      markRead(channelId: string, mark: MarkInput, option?: any): Promise<any>;
      /**
       * Add members to the private channel.
       *
       * @async
       * @function addUsers
       * @param {string} channelId - An private channel id.
       * @param {string[]} userIds - An array of member ids to be added.
       * @param {any} options
       * @returns {Promise<any>}
       */
      addUsers(channelId: string, userIds: any, options: any): Promise<any>;
      /**
       * Remove members from the private channel.
       *
       * @async
       * @function removeUsers
       * @param {string} channelId - An private channel id.
       * @param {string[]} userIds - An array of the member ids to be deleted.
       * @param {any} options
       * @returns {Promise<any>}
       */
      removeUsers(channelId: string, userIds: any, options: any): Promise<any>;
      /**
       * Emit "start typing" to a conneted socket.
       *
       * @async
       * @function startTyping
       * @param {string} channelId - The id of a channel that the typing is happening.
       * @param {string} threadId - The id of a thread that the typing is happening.
       * @returns {Promise<any>}
       */
      startTyping(channelId: string, threadId?: string): Promise<{
          channelId: string;
          threadId: string;
      }>;
      /**
       * Emit "stop typing" to a connected socket.
       *
       * @async
       * @function
       * @param {string} channelId - The id of a channel that the typing is stopped.
       * @param {string} threadId - The id of a thread that the typing is stopped.
       * @returns {Promise<any>}
       */
      stopTyping(channelId: string, threadId?: string): Promise<{
          channelId: string;
          threadId: string;
      }>;
      /**
       * Ban a member from a channel.
       *
       * @async
       * @function banUser
       * @param {string} channelId - The id of a channel.
       * @param {string} memberId - The id of a member.
       * @param {any} options
       * @returns {Promise<any>}
       */
      banUser(channelId: string, memberId: string, options?: any): Promise<any>;
      /**
       * Unban a member from a channel.
       *
       * @async
       * @function unbanUser
       * @param {string} channelId - The id of a channel.
       * @param {string} memberId - The id of a member.
       * @returns {Promise<any>}
       */
      unbanUser(channelId: string, memberId: string): Promise<any>;
      /**
       * Get data of a single pin from the endpoint.
       *
       * @async
       * @function getPin
       * @param {string} channelId - An id of the channel.
       * @param {string} id - An id of the pin.
       * @returns {Promise<any>} The data of the pin.
       */
      getPin(channelId: string, id: string): Promise<any>;
      /**
       * Get data of multiple pins.
       *
       * @async
       * @function getPins
       * @param {string} channelId - An id of the channel.
       * @param {string} filter - Field value for filter.
       * @param {string} sort - Field value for sorting.
       * @param {string} option - Optional option: ex) offset, per_page.
       * @returns {Promise<any[]>} An array of data of all channels.
       */
      getPins(channelId: string, filter: any, sort?: any, option?: any): Promise<any>;
      /**
       * Create a new pin.
       *
       * @async
       * @function createPin
       * @param {string} channelId - An id of the channel.
       * @param {PinInput} pin - Configuration options of the new pin.
       * @returns {Promise<any>} The data of the newly created pin.
       */
      createPin(channelId: string, pin: PinInput): Promise<any>;
      /**
       * Update pin options.
       *
       * @async
       * @function updatePin
       * @param {string} channelId - An id of the channel.
       * @param {PinInput} pin - New options of the pin.
       * @returns {Promise<any>} The data of the updated pin.
       */
      updatePin(pinId: string, channelId: string, pin: PinInput): Promise<any>;
      getServerUrl(): Promise<any>;
  }

}
declare module 'cloudchat/CoreManager' {
  /**
   * Copyright (c) NBASE CORP. and its affiliates.
   *
   * This source code is licensed under the MIT license found in the
   * LICENSE file in the root directory of this source tree.
   */
  const CoreManager: {
      get: (key: string) => any;
      set: (key: string, value: any) => void;
  };
  export default CoreManager;

}
declare module 'cloudchat/Dispatcher' {
  /**
   * Copyright (c) NBASE CORP. and its affiliates.
   *
   * This source code is licensed under the MIT license found in the
   * LICENSE file in the root directory of this source tree.
   */
  /**
   * Class DispatcherEvent holds all dispatch events functionallities with callbacks.
   *
   * @class
   */
  class DispatcherEvent {
      private callbacks;
      /**
       * Create a Dispatcher Event instance with an empty callback.
       *
       * @constructs
       */
      constructor();
      getCallbacks(): any[];
      register(cb: any): void;
      unregister(cb: any): void;
      execute(param1: any, param2: any, param3: any, param4: any, param5: any, param6: any): void;
  }
  type DispatcherProperty = {
      [key: string]: DispatcherEvent;
  };
  export default class Dispatcher {
      private eventProvider;
      constructor();
      getProvider(): DispatcherProperty;
      dispatch(name: string, param1: any, param2?: any, param3?: any, param4?: any, param5?: any, param6?: any): void;
      on(name: string, callback: any): void;
      off(name: string, callback: any): void;
      offall(name: string): void;
  }
  export {};

}
declare module 'cloudchat/Network' {
  /**
   * Copyright (c) NBASE CORP. and its affiliates.
   *
   * This source code is licensed under the MIT license found in the
   * LICENSE file in the root directory of this source tree.
   */
  export const fetchData: (returnKey: string | undefined, query: string, variables: object) => Promise<any>;

}
declare module 'cloudchat/Type' {
  /**
   * Copyright (c) NBASE CORP. and its affiliates.
   *
   * This source code is licensed under the MIT license found in the
   * LICENSE file in the root directory of this source tree.
   */
  /**
   * Enum for channel types.
   * @readonly
   * @enum {string}
   */
  export enum ChannelType {
      PRIVATE = "PRIVATE",
      PUBLIC = "PUBLIC"
  }
  /**
   * Interface for mark input.
   *
   * @interface
   */
  export interface MarkInput {
      user_id: string;
      message_id: string;
      sort_id: string;
  }
  /**
   * Interface for channel input.
   *
   * @interface
   */
  export interface ChannelInput {
      id: string;
      type: ChannelType;
      name: string;
      uniqueId: string;
      translation: boolean;
      disabled: boolean;
      push: boolean;
      mutes: boolean;
      linkUrl: string;
      customField: string;
      imageUrl: string;
      members: string[];
  }
  /**
   * Enum for message types.
   * @readonly
   * @enum {string}
   */
  export enum MessageType {
      text = "text",
      photo = "photo",
      file = "file"
  }
  /**
   * Interface for message input.
   *
   * @interface
   */
  export interface MessageInput {
      messageType: MessageType;
      text: string;
      imageUrls: string;
      fileUrls: string;
      thumbUrls: string;
      mentiones: string;
      customData: string[];
  }
  /**
   *
   * Interface for pin input.
   *
   * @interface
   */
  export interface PinInput {
      pinned: boolean;
      messageId: string;
      pinnedAt: string;
      expiredAt: string;
  }

}
declare module 'cloudchat/Util' {
  /**
   * Copyright (c) NBASE CORP. and its affiliates.
   *
   * This source code is licensed under the MIT license found in the
   * LICENSE file in the root directory of this source tree.
   */
  /**
   * Decodes a base-64 encoded string.
   *
   * @function ObjectId
   * @param {string} id - An encoded string to be decoded.
   * @returns {string} decoded string.
   */
  const ObjectId: (id: string) => string;
  export { ObjectId };

}
declare module 'cloudchat/graphql/channel' {
  /**
   * Copyright (c) NBASE CORP. and its affiliates.
   *
   * This source code is licensed under the MIT license found in the
   * LICENSE file in the root directory of this source tree.
   */
  export const getChannelQuery: string;
  export const getChannelsQuery: string;
  export const createChannelQuery: string;
  export const updateChannelQuery: string;
  export const deleteChannelQuery: string;
  export const addChannelMembersQuery: string;
  export const removeChannelMembersQuery: string;

}
declare module 'cloudchat/graphql/friend' {
  /**
   * Copyright (c) NBASE CORP. and its affiliates.
   *
   * This source code is licensed under the MIT license found in the
   * LICENSE file in the root directory of this source tree.
   */
  export const requestFriendQuery: string;
  export const acceptFriendQuery: string;
  export const rejectFriendQuery: string;
  export const removeFriendQuery: string;
  export const getFriendshipsQuery: string;

}
declare module 'cloudchat/graphql/invite' {
  /**
   * Copyright (c) NBASE CORP. and its affiliates.
   *
   * This source code is licensed under the MIT license found in the
   * LICENSE file in the root directory of this source tree.
   */
  export const createInviteQuery = "mutation (\n        $projectId: String!, \n        $channelId: String!,\n        $friendIds: String!\n    ) {\n        createInvite(\n            input: {\n                projectId: $projectId, \n                channelId: $channelId,\n                friendIds: $friendIds\n            }\n        ) {\n            invite {\n                id\n            }\n        }\n    }\n";

}
declare module 'cloudchat/graphql/member' {
  /**
   * Copyright (c) NBASE CORP. and its affiliates.
   *
   * This source code is licensed under the MIT license found in the
   * LICENSE file in the root directory of this source tree.
   */
  export const loginQuery = "mutation login(\n        $projectId: String!\n        $userId: String!\n        $name: String\n        $profile: String\n        $customField: String\n        ) {\n        login(\n            input: {\n                projectId: $projectId\n                userId: $userId\n                name: $name\n                profile: $profile\n                customField: $customField\n            }\n        ) {\n            token\n        }\n    }";
  export const createMemberBlockQuery: string;
  export const updateMemberQuery = "mutation updateMember(\n    $id: String!\n    $projectId: String!\n    $name: String\n    $profile: String\n    $remoteip: String\n    $memo: String\n    $adid: String\n    $device: String\n    $deviceType: [String]\n    $network: String\n    $version: String\n    $model: String\n    $notications: NoticationInput\n  )\n {\n    updateMember(input: {id: $id, projectId: $projectId, profile: $profile, memo: $memo, name: $name, remoteip: $remoteip, adid: $adid, device: $device, deviceType: $deviceType, network: $network, version: $version, model: $model, notications: $notications}) {\n        member {\n             id\n            project_id\n            name\n            profile\n            country\n            memo\n            remoteip\n            adid\n            device\n            network\n            push\n            version\n            model\n            logined_at\n            created_at\n            updated_at\n            notications {\n                token\n                device\n                os\n            }\n        }\n    }\n}\n";
  export const deleteMemberBlockQuery: string;
  export const getMembersQuery = "query membersForQuery (\n        $projectId: String!, \n        $option: String!, \n        $filter: String!, \n        $sort: String \n    ) {\n        membersForQuery (\n            projectId: $projectId, \n            option:$option, \n            filter:$filter, \n            sort:$sort\n        ) {\n            totalCount\n            edges {\n                node {\n                    id\n                    name\n                }\n            }\n        }\n    }\n";
  export const getMemberBlocksQuery = "query memberblocks (\n        $projectId: String!, \n        $filter: String!, \n        $sort: String, \n        $option:String\n    ) {\n        memberblocks(\n            projectId: $projectId, \n            filter:$filter, \n            sort:$sort, \n            option:$option\n        ) {\n            totalCount\n            edges {\n                node {\n                    id\n                    project_id\n                    member_id\n                    type\n                    status\n                    block_type\n                    messageMulti {\n                        lang\n                        value\n                        default\n                    }\n                    started_at\n                    ended_at\n                    created_at\n                    updated_at\n                    deleted_at\n                }\n            }\n        }\n    }\n";

}
declare module 'cloudchat/graphql/message' {
  /**
   * Copyright (c) NBASE CORP. and its affiliates.
   *
   * This source code is licensed under the MIT license found in the
   * LICENSE file in the root directory of this source tree.
   */
  export const translateQuery = "mutation (\n        $projectId: String!, \n        $channelId: String!, \n        $srcLang: String!, \n        $text: String!, \n        $targetLang:String!\n    ) {\n        translation(\n            input: {\n                projectId:$projectId, \n                channelId:$channelId,\n                srcLang:$srcLang,\n                targetLang:$targetLang, \n                text:$text \n            }\n        ) {\n            result\n            {\n                status\n                message\n                lang\n            }\n        }\n    }\n";
  export const getMessageQuery: string;
  export const getMessagesQuery: string;
  export const unreadCountQuery = "query mark (\n        $projectId: String!, \n        $channelId: String!\n    ) { \n        mark (\n            projectId: $projectId, \n            channelId:$channelId\n        ) {  \n            user_id,\n            message_id,\n            sort_id,  \n            unread\n        } \n    }\n";
  export const messageQuery: string;
  export const deleteMessageQuery: string;

}
declare module 'cloudchat/graphql/pin' {
  /**
   * Copyright (c) NBASE CORP. and its affiliates.
   *
   * This source code is licensed under the MIT license found in the
   * LICENSE file in the root directory of this source tree.
   */
  export const getPinQuery: string;
  export const getPinsQuery: string;
  export const createPinQuery: string;
  export const updatePinQuery: string;

}
declare module 'cloudchat/graphql/project' {
  /**
   * Copyright (c) NBASE CORP. and its affiliates.
   *
   * This source code is licensed under the MIT license found in the
   * LICENSE file in the root directory of this source tree.
   */

}
declare module 'cloudchat/graphql/subscription' {
  /**
   * Copyright (c) NBASE CORP. and its affiliates.
   *
   * This source code is licensed under the MIT license found in the
   * LICENSE file in the root directory of this source tree.
   */
  export const createSubscriptionQuery: string;
  export const deleteSubscriptionQuery: string;
  export const updateSubscriptionQuery: string;
  export const getSubscriptionQuery: string;
  export const getSubscriptionsQuery: string;

}
declare module 'cloudchat/index' {
  /**
   * Copyright (c) NBASE CORP. and its affiliates.
   *
   * This source code is licensed under the MIT license found in the
   * LICENSE file in the root directory of this source tree.
   */
  import Chat from 'cloudchat/CloudChat';
  export { Chat };

}
declare module 'cloudchat/logger' {
  /**
   * Copyright (c) NBASE CORP. and its affiliates.
   *
   * This source code is licensed under the MIT license found in the
   * LICENSE file in the root directory of this source tree.
   */
  const debug: (value: any) => void;
  const error: (value: any) => void;
  const info: (value: any) => void;
  export { debug, error, info };

}
declare module 'cloudchat/mutations/channel' {
  /**
   * Copyright (c) NBASE CORP. and its affiliates.
   *
   * This source code is licensed under the MIT license found in the
   * LICENSE file in the root directory of this source tree.
   */
  import type { ChannelInput } from "cloudchat/Type";
  /**
   * Create a new channel.
   *
   * @async
   * @function createChannel
   * @param {ChannelInput} channel - Configuration options of the new channel.
   * @returns {Promise<any>} The data of the newly created channel.
   */
  export const createChannel: (channel: ChannelInput) => Promise<any>;
  /**
   * Update channel options.
   *
   * @async
   * @function updateChannel
   * @param {string} channelId - An id of the channel.
   * @param {ChannelInput} channel - New options of the channel.
   * @returns {Promise<any>} The data of the updated channel.
   */
  export const updateChannel: (channelId: string, channel: ChannelInput) => Promise<any>;
  /**
   * Delete a channel.
   *
   * @async
   * @function deleteChannel
   * @param {string} channelId - An channel id.
   * @returns {Promise<any>}
   */
  export const deleteChannel: (channelId: string) => Promise<any>;
  /**
   * Add members to the private channel.
   *
   * @async
   * @function addChannelMembers
   * @param {string} channelId - An private channel id.
   * @param {string[]} memberIds - An array of member ids to be added.
   * @param {any} options
   * @returns {Promise<any>}
   */
  export const addChannelMembers: (channelId: string, memberIds: any, options?: any) => Promise<any>;
  /**
   * Remove members from the private channel.
   *
   * @async
   * @function removeChannelMembers
   * @param {string} channelId - An private channel id.
   * @param {string[]} memberIds - An array of the member ids to be deleted.
   * @returns {Promise<any>}
   */
  export const removeChannelMembers: (channelId: string, memberIds: any, options?: any) => Promise<any>;

}
declare module 'cloudchat/mutations/friend' {
  /**
   * Copyright (c) NBASE CORP. and its affiliates.
   *
   * This source code is licensed under the MIT license found in the
   * LICENSE file in the root directory of this source tree.
   */
  /**
   * Request a friendship to a user.
   *
   * @async
   * @function requestFriend
   * @param {string} friendId - An id of the friend to request.
   * @returns {Promise<any>}
   */
  export const requestFriend: (friendId: string) => Promise<any>;
  /**
   * Accept a friends request.
   *
   * @async
   * @function acceptFriend
   * @param {string} friendId - The id of the friend to accept the request.
   * @returns {Promise<any>}
   */
  export const acceptFriend: (friendId: string) => Promise<any>;
  /**
   * Reject a friend request.
   *
   * @async
   * @function rejectFriend
   * @param {string} friendId - An id of the friend to be rejected.
   * @returns {Promise<any>}
   */
  export const rejectFriend: (friendId: string) => Promise<any>;
  /**
   * Remove a friend.
   *
   * @async
   * @function removeFriend
   * @param {string} friendId - An id of the friend to be removed.
   * @returns {Promise<any>}
   */
  export const removeFriend: (friendId: string) => Promise<any>;

}
declare module 'cloudchat/mutations/index' {
  /**
   * Copyright (c) NBASE CORP. and its affiliates.
   *
   * This source code is licensed under the MIT license found in the
   * LICENSE file in the root directory of this source tree.
   */
  export * from 'cloudchat/mutations/member';
  export * from 'cloudchat/mutations/channel';
  export * from 'cloudchat/mutations/message';
  export * from 'cloudchat/mutations/friend';
  export * from 'cloudchat/mutations/invite';
  export * from 'cloudchat/mutations/subscription';
  export * from 'cloudchat/mutations/pin';

}
declare module 'cloudchat/mutations/invite' {
  /**
   * Copyright (c) NBASE CORP. and its affiliates.
   *
   * This source code is licensed under the MIT license found in the
   * LICENSE file in the root directory of this source tree.
   */
  /**
   * Create an invite to a friend users to a channel.
   *
   * @async
   * @function createInvite
   * @param {string} channelId - An id of the channel to be invited.
   * @param {string} friendIds - Id of friends to be invited to the channel.
   * @returns {Promise<any>}
   */
  export const createInvite: (channelId: string, friendIds: any) => Promise<any>;

}
declare module 'cloudchat/mutations/member' {
  /**
   * Copyright (c) NBASE CORP. and its affiliates.
   *
   * This source code is licensed under the MIT license found in the
   * LICENSE file in the root directory of this source tree.
   */
  /**
   * Login a user
   *
   * @async
   * @function login
   * @param {string} user_id - The id of a user loggin in.
   * @param {string} name
   * @param {string} profile
   * @param {string} token
   * @returns {Promise<any>}
   */
  export const login: (user_id: string, name?: string, profile?: string, token?: string, customField?: string) => Promise<any>;
  /**
   * Create a member block followed by the getMemberBlockSchema.
   * The member block is to used to ban a member.
   *
   * @async
   * @function createMemberBlock
   * @param {string} channelId - The id of a channel.
   * @param {string} memberId - The id of a member.
   * @param {string} options
   * @returns {Promise<any>}
   */
  export const createMemberBlock: (channelId: string, memberId: string, options?: string) => Promise<any>;
  /**
   * Delete a member block.
   * This is used to unban a member from a channel.
   *
   * @async
   * @function deleteMemberBlock
   * @param {string} channelId - The id of a channel that the member is in.
   * @param {string} memberId - The id of a member to be deleted.
   * @returns {Promise<any>}
   */
  export const deleteMemberBlock: (channelId: string, memberId: string) => Promise<any>;
  export const updateMember: (user_id: string, update: any) => Promise<any>;

}
declare module 'cloudchat/mutations/message' {
  /**
   * Copyright (c) NBASE CORP. and its affiliates.
   *
   * This source code is licensed under the MIT license found in the
   * LICENSE file in the root directory of this source tree.
   */
  /**
   * Trasnslate a text block to the target language from the source language.
   *
   * @async
   * @function translate
   * @param {string} channelId - The id of a channel.
   * @param {string} srcLang - The source language.
   * @param {string} targetLang - The target language.
   * @param {string} text - The text block to be translated.
   * @returns {Promise<any>}
   */
  export const translate: (channelId: string, srcLang: string, targetLang: string, text: string) => Promise<any>;
  /**
   * Upload a file to a channel. Only supports png, jpg, jpeg file types.
   *
   * @async
   * @function upload
   * @param {string} channelId - The id of a channel.
   * @param {any} file - An image file.
   * @returns {Promise<any>}
   */
  export const upload: (channelId: string, file: any) => Promise<any>;
  /**
   * Send a Message
   *
   * @async
   * @function message
   * @param {string} data - The id of a message.
   * @returns {Promise<any>}
   */
  export const message: (data: any) => Promise<any>;
  /**
   *
   * @sync
   * @function deleteMessage
   * @param {string} data - The id of a message.
   * @returns {Promise<any>}
   */
  export const deleteMessage: (channelId: string, messageId: string) => Promise<any>;

}
declare module 'cloudchat/mutations/pin' {
  /**
   * Copyright (c) NBASE CORP. and its affiliates.
   *
   * This source code is licensed under the MIT license found in the
   * LICENSE file in the root directory of this source tree.
   */
  import type { PinInput } from "cloudchat/Type";
  /**
  * Create a new pin.
  *
  * @async
  * @function createPin
  * @param {string} channelId - An id of the channel.
  * @param {PinInput} pin - Configuration options of the new pin.
  * @returns {Promise<any>} The data of the newly created pin.
  */
  export const createPin: (channelId: string, pin: PinInput) => Promise<any>;
  /**
   * Update pin options.
   *
   * @async
   * @function updatePin
   * @param {string} id - An id of the pin.
   * @param {string} channelId - An id of the channel.
   * @param {PinInput} pin - New options of the pin.
   * @returns {Promise<any>} The data of the updated created pin.
   */
  export const updatePin: (id: string, channelId: string, pin: PinInput) => Promise<any>;

}
declare module 'cloudchat/mutations/subscription' {
  /**
   * Copyright (c) NBASE CORP. and its affiliates.
   *
   * This source code is licensed under the MIT license found in the
   * LICENSE file in the root directory of this source tree.
   */
  import type { MarkInput } from "cloudchat/Type";
  /**
   * Create a subscription.
   *
   * @async
   * @function createSubscription
   * @param {string} channelId - The id of a channel.
   * @param {string} option
   * @returns {Promise<any>}
   */
  export const createSubscription: (channelId: string, option?: string) => Promise<any>;
  /**
   * Delete a subscription.
   *
   * @async
   * @function deleteSubscription
   * @param {string} channelId - The id of a channel.
   * @returns {Promise<any>}
   */
  export const deleteSubscription: (channelId: string) => Promise<any>;
  /**
   * Update a subscription information, such as marking message read.
   *
   * @async
   * @function updateSubscription
   * @param {string} channelId - The id of a channel.
   * @param {MarkInput} mark
   * @param {string} option
   * @returns {Promise<any>}
   */
  export const updateSubscription: (channelId: string, mark: MarkInput, option?: string) => Promise<any>;

}
declare module 'cloudchat/queries/channel' {
  /**
   * Copyright (c) NBASE CORP. and its affiliates.
   *
   * This source code is licensed under the MIT license found in the
   * LICENSE file in the root directory of this source tree.
   */
  /**
   * Get data of a single channel from the endpoint.
   *
   * @async
   * @function getChannel
   * @param {string} id - An id of the channel.
   * @returns {Promise<any>} The data of the channel.
   */
  export const getChannel: (id: string) => Promise<any>;
  /**
   * Get data of multiple channels.
   *
   * @async
   * @function getChannels
   * @param {string} filter - Field value for filter.
   * @param {string} sort - Field value for sorting.
   * @param {string} option - Optional option: ex) offset, per_page.
   * @returns {Promise<any[]>} An array of data of all channels.
   */
  export const getChannels: (filter: string, sort: string, option: string) => Promise<any>;

}
declare module 'cloudchat/queries/friend' {
  /**
   * Copyright (c) NBASE CORP. and its affiliates.
   *
   * This source code is licensed under the MIT license found in the
   * LICENSE file in the root directory of this source tree.
   */
  /**
   * Get current friends list of all status.
   *
   * @async
   * @function getFriendships
   * @param {string} filter - Field value for filter.
   * @param {string} sort - Field value for sorting.
   * @param {string} option - Optional option: ex) offset, per_page.
   * @returns {Promise<any>}
   */
  export const getFriendships: (filter: string, sort: string, option: string) => Promise<any>;

}
declare module 'cloudchat/queries/index' {
  /**
   * Copyright (c) NBASE CORP. and its affiliates.
   *
   * This source code is licensed under the MIT license found in the
   * LICENSE file in the root directory of this source tree.
   */
  export * from 'cloudchat/queries/project';
  export * from 'cloudchat/queries/channel';
  export * from 'cloudchat/queries/message';
  export * from 'cloudchat/queries/subscription';
  export * from 'cloudchat/queries/friend';
  export * from 'cloudchat/queries/member';
  export * from 'cloudchat/queries/memberblocks';
  export * from 'cloudchat/queries/pin';

}
declare module 'cloudchat/queries/member' {
  /**
   * Copyright (c) NBASE CORP. and its affiliates.
   *
   * This source code is licensed under the MIT license found in the
   * LICENSE file in the root directory of this source tree.
   */
  /**
   * Get information data of the member.
   *
   * @async
   * @function getMembers
   * @param {string} filter - Field value for filter.
   * @param {string} sort - Field value for sorting.
   * @param {string} option - Optional option: ex) offset, per_page.
   * @returns {Promise<any>}
   */
  export const getMembers: (filter: string, sort: string, option: string) => Promise<any>;

}
declare module 'cloudchat/queries/memberblocks' {
  /**
   * Copyright (c) NBASE CORP. and its affiliates.
   *
   * This source code is licensed under the MIT license found in the
   * LICENSE file in the root directory of this source tree.
   */
  /**
   * Get member blocks.
   *
   * @async
   * @function getMemberBlocks
   * @param {string} filter - Field value for filter.
   * @param {string} sort - Field value for sorting.
   * @param {string} option - Optional option: ex) offset, per_page.
   * @returns {Promise<any>}
   */
  export const getMemberBlocks: (filter: string, sort: string, option: string) => Promise<any>;

}
declare module 'cloudchat/queries/message' {
  /**
   * Copyright (c) NBASE CORP. and its affiliates.
   *
   * This source code is licensed under the MIT license found in the
   * LICENSE file in the root directory of this source tree.
   */
  /**
   * Get data of the all messages.
   *
   * @async
   * @function getMessages
   * @param {string} filter - Field value for filter.
   * @param {string} sort - Field value for sorting.
   * @param {string} option - Optional option: ex) offset, per_page.
   * @returns {Promise<any>}
   */
  export const getMessages: (filter: string, sort: string, option: string) => Promise<any>;
  /**
   * Get data of a single message.
   *
   * @async
   * @function getMessage
   * @param {string} channelId - The id of a channel.
   * @param {string} messageId - The id of a message.
   * @returns {Promise<any>}
   */
  export const getMessage: (channelId: string, messageId: string) => Promise<any>;
  /**
   * Get a count of unread messages of a channel.
   *
   * @async
   * @function unreadCount
   * @param {string} channelId - The id of a channel.
   * @returns {Promise<any>}
   */
  export const unreadCount: (channelId: string) => Promise<any>;

}
declare module 'cloudchat/queries/pin' {
  /**
   * Copyright (c) NBASE CORP. and its affiliates.
   *
   * This source code is licensed under the MIT license found in the
   * LICENSE file in the root directory of this source tree.
   */
  /**
   * Get data of a single pin from the endpoint.
   *
   * @async
   * @function getPin
   * @param {string} channelId - An id of the channel.
   * @param {string} id - An id of the pin.
   * @returns {Promise<any>} The data of the pin.
   */
  export const getPin: (channelId: string, id: string) => Promise<any>;
  /**
   * Get data of multiple pins.
   *
   * @async
   * @function getPins
   * @param {string} channelId - An id of the channel.
   * @param {string} filter - Field value for filter.
   * @param {string} sort - Field value for sorting.
   * @param {string} option - Optional option: ex) offset, per_page.
   * @returns {Promise<any[]>} An array of data of all pins.
   */
  export const getPins: (channelId: string, filter: string, sort: string, option: string) => Promise<any>;

}
declare module 'cloudchat/queries/project' {
  /**
   * Copyright (c) NBASE CORP. and its affiliates.
   *
   * This source code is licensed under the MIT license found in the
   * LICENSE file in the root directory of this source tree.
   */
  export {};
  /**
   * Get data of a project.
   *
   * @async
   * @function getProject
   * @returns {Promise<any>}
   */

}
declare module 'cloudchat/queries/subscription' {
  /**
   * Copyright (c) NBASE CORP. and its affiliates.
   *
   * This source code is licensed under the MIT license found in the
   * LICENSE file in the root directory of this source tree.
   */
  /**
   * Get subscription data of the user.
   *
   * @async
   * @function getSubscription
   * @param {string} channelId - The id of a channel.
   * @param {string} id
   * @returns {Promise<any>}
   */
  export const getSubscription: (channelId: string, id: string) => Promise<any>;
  /**
   * Get a list of subscibed member of the channel.
   *
   * @async
   * @function getSubscriptions
   * @param {string} filter  - Field value for filter.
   * @param {string} sort - Field value for sorting.
   * @param {string} option - Optional option: ex) offset, per_page.
   * @returns {Promise<any>}
   */
  export const getSubscriptions: (filter: any, sort: any, option: any) => Promise<any>;

}
declare module 'cloudchat' {
  import main = require('cloudchat/index');
  export = main;
}