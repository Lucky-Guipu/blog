<template>
  <div class="login_container">
    	<div class="login_box">
        <!--头像-->
        <div class="avatar_box">
          <img src="/img/avatar.jpg" alt="">
        </div>
        
        <!--登录表单-->
        <el-form ref="loginFormRef" :model="loginForm" :rules="loginFormRules" class="login_form">
          <el-form-item prop="username">
            <el-input v-model="loginForm.username" prefix-icon="User"></el-input>
          </el-form-item>

          <el-form-item prop="password">
            <el-input v-model="loginForm.password" prefix-icon="Lock" show-password @keyup.native.enter="login"></el-input>
          </el-form-item>

          <el-form-item>
            <div class="btns">
              <el-button type="primary" @click="login" >登录</el-button>
              <el-button type="info" @click="resetLoginForm">重置</el-button>
            </div>
          </el-form-item>

        </el-form>
      </div>
  </div>
</template>

<script>
  export default {
    name: 'Login',
    data () {
      return {
        loginForm: {
          username: '',
          password: ''
        },
        loginFormRules: {
					username: [
						{required: true, message: '请输入用户名', trigger: 'blur'},
					],
					password: [
						{required: true, message: '请输入密码', trigger: 'blur'},
					]
				},
        // responseResult: []
      }
    },
    methods: {
      login () {
        this.$axios
          .post('/login', {
            username: this.loginForm.username,
            password: this.loginForm.password
          })
          .then(successResponse => {
            if (successResponse.data.code === 200) {
              this.$router.replace({path: '/index'})
            }
          })
          .catch(failResponse => {
          })
      },
      resetLoginForm () {
        this.$refs.loginFormRef.resetFields();
      }
    }

  }
</script>

<style scoped>
	.login_container {
    box-sizing: border-box;
    position: fixed;
    top: 0;
    left: 0;
    width: 100vw;
    height: 100vh;
		background-color: #2b4b6b;
	}
  
  .login_box {
		width: 450px;
		height: 300px;
		background-color: #fff;
		border-radius: 3px;
		position: absolute;
		left: 50%;
		top: 50%;
		transform: translate(-50%, -50%);
	}
	
  .login_box .avatar_box {
		height: 130px;
		width: 130px;
		border: 1px solid #eee;
		border-radius: 50%;
		padding: 10px;
		box-shadow: 0 0 10px #ddd;
		position: absolute;
		left: 50%;
		transform: translate(-50%, -50%);
		background-color: #fff;
	}

  .login_box .avatar_box img {
		width: 100%;
		height: 100%;
		border-radius: 50%;
		background-color: #eee;
	}

  .login_form {
		position: absolute;
		bottom: 20px;
		width: 100%;
		padding: 0 20px;
		box-sizing: border-box;
	}

  .btns {
    width: 100%;
		display: flex;
		justify-content: flex-end;
	}

</style>