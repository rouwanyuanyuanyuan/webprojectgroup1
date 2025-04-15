<template>
  <div class="login-container">
    <el-form
      ref="loginForm"
      :model="loginForm"
      :rules="loginRules"
      class="login-form"
      auto-complete="on"
      label-position="left"
    >
      <!-- 标题 -->
      <div class="title-container">
        <h3 class="title">注册新账号</h3>
      </div>
      <!-- 用户名 -->
      <el-form-item prop="username">
                <span class="svg-container">
                    <i class="el-icon-user"></i>
                </span>
        <el-input
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
          ref="password"
          v-model="loginForm.password"
          type="password"
          placeholder="请输入密码"
          name="password"
          tabindex="2"
          auto-complete="on"
        />
      </el-form-item>
      <!-- 确认密码 -->
      <el-form-item prop="repeat">
                <span class="svg-container">
                    <i class="el-icon-key"></i>
                </span>
        <el-input
          ref="repeat"
          v-model="loginForm.repeat"
          type="password"
          placeholder="请确认密码"
          name="repeat"
          tabindex="3"
          auto-complete="on"
          @keyup.enter.native="handleRight"
        />
      </el-form-item>

      <!-- 按钮 -->
      <div style="height: 40px; margin-bottom: 30px">
        <el-button
          :loading="loading"
          type="primary"
          style="width: 48%; float: left; border-radius: 20px; background: #ff7eb9; border: none;"
          @click.native.prevent="handleRight"
        >确认</el-button
        >
        <el-button
          :loading="loading"
          type="success"
          style="width: 48%; float: right; border-radius: 20px; background: #ff9ac1; border: none;"
          @click.native.prevent="handleBack"
        >返回登录</el-button
        >
      </div>
    </el-form>
  </div>
</template>

<script>
import { register } from "@/api/user";

export default {
  name: "Register",
  data() {
    const validateRepeat = (rule, value, callback) => {
      if (value !== this.loginForm.password) {
        callback(new Error("两次输入的密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      loginForm: {
        username: "",
        password: "",
        repeat: "",
      },
      loginRules: {
        username: [
          {
            required: true,
            message: "请输入用户名",
            trigger: "blur",
          },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
        ],
        repeat: [
          {
            required: true,
            message: "请再次输入密码",
            trigger: "blur",
          },
          { trigger: "blur", validator: validateRepeat },
        ],
      },
      loading: false,
    };
  },
  methods: {
    handleRight() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          this.loading = true;
          register({
            username: this.loginForm.username,
            password: this.loginForm.password,
          }).then((res) => {
            this.loading = false;
            if (res === 0) {
              this.$message.error("注册失败，可能账号重复了");
            } else {
              this.$message.success("注册成功");
              setTimeout(() => {
                this.$router.push("/login");
              }, 1500);
            }
          }).catch(() => {
            this.loading = false;
          });
        } else {
          console.log("不允许提交!");
          return false;
        }
      });
    },
    handleBack() {
      this.$router.push("/login");
    },
  },
};
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
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
  .el-input {
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
}
</style>
