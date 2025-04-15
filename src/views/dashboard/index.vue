<template>
  <div class="dashboard-container">
    <div class="welcome-container">
      <div class="welcome-header">
        <i class="el-icon-reading" style="font-size: 32px; margin-right: 10px;"></i>
        <span class="welcome-title">欢迎使用图书管理系统</span>
      </div>
      <div class="welcome-divider"></div>

      <div class="welcome-content">
        <img :src="require('@/assets/index.png')" class="welcome-image" />
        <div class="welcome-message">
          <p>亲爱的{{ name }}，欢迎回来！</p>
          <p>今天是 {{ currentDate }}，祝您阅读愉快~</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  name: "Dashboard",
  data() {
    return {
      currentDate: this.formatDate(new Date())
    };
  },
  computed: {
    ...mapGetters(["id", "name", "roles"]),
  },
  methods: {
    formatDate(date) {
      const year = date.getFullYear();
      const month = (date.getMonth() + 1).toString().padStart(2, '0');
      const day = date.getDate().toString().padStart(2, '0');
      const weekdays = ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'];
      const weekday = weekdays[date.getDay()];
      return `${year}年${month}月${day}日 ${weekday}`;
    }
  }
};
</script>

<style lang="scss" scoped>
.dashboard-container {
  min-height: calc(100vh - 50px);
  width: 100%;
  background: linear-gradient(135deg, #fff0f5 0%, #ffd6e0 100%);
  padding: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  font-family: 'Arial Rounded MT Bold', 'Arial', sans-serif;
}

.welcome-container {
  width: 90%;
  max-width: 1200px;
  background: rgba(255, 255, 255, 0.9);
  border-radius: 20px;
  padding: 40px;
  box-shadow: 0 10px 30px rgba(255, 126, 185, 0.2);
  text-align: center;
}

.welcome-header {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 20px;
}

.welcome-title {
  font-size: 28px;
  font-weight: bold;
  color: #ff7eb9;
  text-shadow: 0 2px 4px rgba(255, 126, 185, 0.2);
}

.welcome-divider {
  height: 2px;
  background: linear-gradient(to right, transparent, #ff9ac1, transparent);
  margin: 20px 0;
}

.welcome-content {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.welcome-image {
  width: 78%;
  border-radius: 15px;
  box-shadow: 0 5px 15px rgba(255, 126, 185, 0.3);
  margin-bottom: 30px;
  transition: transform 0.3s ease;

  &:hover {
    transform: scale(1.02);
  }
}

.welcome-message {
  font-size: 18px;
  color: #ff7eb9;
  line-height: 1.8;
  text-align: center;

  p {
    margin: 10px 0;
  }
}

@media (max-width: 768px) {
  .welcome-container {
    padding: 20px;
  }

  .welcome-title {
    font-size: 22px;
  }

  .welcome-image {
    width: 100%;
  }
}
</style>
