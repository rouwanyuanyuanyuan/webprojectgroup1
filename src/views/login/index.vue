<template>
  <div class="login-container">
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" auto-complete="on" label-position="left">
      <!-- 标题 -->
      <div class="title-container">
        <h3 class="title">登录图书管理系统</h3>
      </div>
      <!-- 用户名 -->
      <el-form-item prop="username">
        <span class="svg-container">
          <i class="el-icon-user"></i>
        </span>
        <el-input
          class="yuan"
          ref="username"
          v-model="loginForm.username"
          placeholder="请输入用户名"
          name="username"
          type="text"
          tabindex="1"
          auto-complete="on"
        />
      </el-form-item>
      <!-- 密码 -->
      <el-form-item prop="password">
        <span class="svg-container">
          <i class="el-icon-lock"></i>
        </span>
        <el-input
          class="yuan"
          :key="passwordType"
          ref="password"
          v-model="loginForm.password"
          :type="passwordType"
          placeholder="请输入密码"
          name="password"
          tabindex="2"
          auto-complete="on"
          @keyup.enter.native="handleLogin"
        />
        <span class="show-pwd" @click="showPwd">
          <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
        </span>
      </el-form-item>
      <!-- 权限 -->
      <el-form-item prop="authority">
        <span class="svg-container">
          <i class="el-icon-s-custom"></i>
        </span>
        <el-select v-model="loginForm.isadmin" placeholder="请选择" style="width: 418px">
          <el-option :key="0" label="读者" :value="0"></el-option>
          <el-option :key="1" label="管理员" :value="1"></el-option>
        </el-select>
      </el-form-item>

      <!-- 登录按钮 -->
      <div style="height: 40px; margin-bottom: 30px;">
        <el-button :loading="loading" type="primary" style="width: 48%; float: left; border-radius: 20px; background: #ff7eb9; border: none;" @click.native.prevent="handleLogin">登录</el-button>
        <el-button :loading="loading" type="success" style="width: 48%; float: right; border-radius: 20px; background: #ff9ac1; border: none;" @click.native.prevent="handleRegister">注册</el-button>
      </div>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data() {
    const validateUsername = (rule, value, callback) => {
      callback()
    }
    const validatePassword = (rule, value, callback) => {
      callback()
    }
    return {
      loginForm: {
        username: '',
        password: '',
        isadmin: 0
      },
      loginRules: {
        username: [{ required: true, trigger: 'blur', validator: validateUsername }],
        password: [{ required: true, trigger: 'blur', validator: validatePassword }]
      },
      loading: false,
      passwordType: 'password',
      redirect: undefined
    }
  },
  methods: {
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.$store.dispatch('user/login', this.loginForm).then(() => {
            this.$router.push({ path: '/' })  // 无脑进首页
            this.loading = false
          }).catch((message) => {
            this.$message.error(message)
            this.loading = false
          })
        } else {
          console.log('不允许提交!')
          return false
        }
      })
    },
    handleRegister() {
      console.log("注册按钮")
      this.$router.push({ path: '/register' })  // 进注册页面
    }
  }
}
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg: #fff0f5; /* 柔和的粉色背景 */
$light_gray: #ff7eb9; /* 粉色文字 */
$cursor: #ff7eb9; /* 粉色光标 */

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
    &::placeholder {
      color: #ffb6c1; /* 浅粉色占位文字 */
    }
  }
}

/* reset element-ui css */
.login-container {
  .el-input.yuan {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-input {
    display: inline-block;
    height: 47px;
    width: 100%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid #ffb6c1; /* 浅粉色边框 */
    background: rgba(255, 255, 255, 0.7); /* 半透明白色背景 */
    border-radius: 25px; /* 更圆的边框 */
    color: #ff7eb9;
    margin-bottom: 20px;
    box-shadow: 0 4px 8px rgba(255, 126, 185, 0.1); /* 粉色阴影 */
    transition: all 0.3s ease;

    &:hover {
      box-shadow: 0 4px 12px rgba(255, 126, 185, 0.2);
    }
  }

  .el-button {
    font-weight: bold;
    letter-spacing: 1px;
    transition: all 0.3s ease;

    &:hover {
      transform: translateY(-2px);
      box-shadow: 0 4px 12px rgba(255, 126, 185, 0.3);
    }

    &:active {
      transform: translateY(0);
    }
  }
}
</style>

<style lang="scss" scoped>
$bg: linear-gradient(135deg, #ffd6e0 0%, #ff9ac1 100%); /* 粉色渐变背景 */
$dark_gray: #ff7eb9; /* 粉色图标 */
$light_gray: #fff; /* 白色文字 */

.login-container {
  min-height: 100vh;
  width: 100%;
  background: $bg;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
  font-family: 'Arial Rounded MT Bold', 'Arial', sans-serif;

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 60px 35px;
    margin: 0 auto;
    overflow: hidden;
    background: rgba(255, 255, 255, 0.9);
    border-radius: 20px;
    box-shadow: 0 10px 30px rgba(255, 126, 185, 0.2);
  }

  .tips {
    font-size: 14px;
    color: #ff7eb9;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
    font-size: 20px;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      color: #ff7eb9;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
      text-shadow: 0 2px 4px rgba(255, 126, 185, 0.2);
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
}
</style>
