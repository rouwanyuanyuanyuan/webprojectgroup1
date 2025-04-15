<template>
  <div class="navbar">
    <hamburger :is-active="sidebar.opened" class="hamburger-container" @toggleClick="toggleSideBar" />

    <breadcrumb class="breadcrumb-container" />

    <div class="right-menu">
      <el-dropdown class="avatar-container" trigger="click">
        <div class="avatar-wrapper">
          <img :src="avatar" class="user-avatar">
          <span class="user-name">{{name}}</span>
          <i class="el-icon-caret-bottom" />
        </div>
        <el-dropdown-menu slot="dropdown" class="user-dropdown">
          <router-link to="/">
            <el-dropdown-item>
              <i class="el-icon-s-home"></i> 首页
            </el-dropdown-item>
          </router-link>
          <el-dropdown-item divided @click.native="logout">
            <i class="el-icon-switch-button"></i>
            <span style="display:inline-block; margin-left: 5px;">退出登录</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Breadcrumb from '@/components/Breadcrumb'
import Hamburger from '@/components/Hamburger'

export default {
  components: {
    Breadcrumb,
    Hamburger
  },
  computed: {
    ...mapGetters([
      'sidebar',
      'avatar',
      'name'
    ])
  },
  methods: {
    toggleSideBar() {
      this.$store.dispatch('app/toggleSideBar')
    },
    async logout() {
      await this.$store.dispatch('user/logout')
      this.$router.push(`/login`)
    }
  }
}
</script>

<style lang="scss" scoped>
.navbar {
  height: 60px;
  overflow: hidden;
  position: relative;
  background: linear-gradient(to right, #ffd6e0, #ffb6c1);
  box-shadow: 0 2px 10px rgba(255, 126, 185, 0.2);
  border-bottom: 1px solid #ff9ac1;

  .hamburger-container {
    line-height: 60px;
    height: 100%;
    float: left;
    cursor: pointer;
    transition: all .3s;
    padding: 0 15px;
    color: #ff7eb9;

    &:hover {
      background: rgba(255, 255, 255, 0.3);
      transform: scale(1.1);
    }
  }

  .breadcrumb-container {
    float: left;
    font-size: 16px;
    margin-top: 8px;
    color: #ff7eb9;
    font-weight: bold;
    ::v-deep .no-redirect {
      color: #ff7eb9;
    }
  }

  .right-menu {
    float: right;
    height: 100%;
    line-height: 60px;

    &:focus {
      outline: none;
    }

    .avatar-container {
      margin-right: 30px;
      transition: all 0.3s;

      &:hover {
        transform: translateY(-2px);
      }

      .avatar-wrapper {
        margin-top: 5px;
        position: relative;
        display: flex;
        align-items: center;

        .user-avatar {
          cursor: pointer;
          width: 40px;
          height: 40px;
          border-radius: 50%;
          border: 2px solid #fff;
          box-shadow: 0 2px 5px rgba(255, 126, 185, 0.3);
          transition: all 0.3s;

          &:hover {
            transform: scale(1.1);
            box-shadow: 0 4px 10px rgba(255, 126, 185, 0.4);
          }
        }

        .user-name {
          cursor: pointer;
          font-size: 16px;
          color: #fff;
          margin: 0 10px;
          text-shadow: 0 1px 2px rgba(255, 126, 185, 0.5);
        }

        .el-icon-caret-bottom {
          cursor: pointer;
          color: #fff;
          font-size: 14px;
          transition: all 0.3s;
        }
      }
    }
  }
}

.user-dropdown {
  border-radius: 10px !important;
  border: 1px solid #ffb6c1 !important;
  box-shadow: 0 2px 12px rgba(255, 126, 185, 0.2) !important;

  .el-dropdown-menu__item {
    color: #ff7eb9;
    font-size: 14px;
    padding: 10px 20px;
    transition: all 0.3s;

    i {
      color: #ff7eb9;
      margin-right: 5px;
    }

    &:hover {
      background: #ffebf1 !important;
      color: #ff5a8a !important;
      transform: translateX(5px);

      i {
        color: #ff5a8a;
      }
    }
  }

  .popper__arrow {
    border-bottom-color: #ffb6c1 !important;
    &::after {
      border-bottom-color: #fff !important;
    }
  }
}
</style>
