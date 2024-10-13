// src/components/LoginComponent.js
import React, { useState } from 'react';
import axios from 'axios';

const LoginComponent = () => {
  const [loginData, setLoginData] = useState({
    email: '',
    password: '',
  });

  const [message, setMessage] = useState('');

  const handleInputChange = (e) => {
    setLoginData({
      ...loginData,
      [e.target.name]: e.target.value,
    });
  };

  const handleLogin = async () => {
    if (!loginData.email || !loginData.password) {
      setMessage('Both email and password are required.');
      return;
    }

    try {
      const response = await axios.post('http://localhost:8080/login', loginData);
      setMessage(`Login successful. Welcome, ${response.data.name}!`);
    } catch (error) {
      if (error.response.status === 401) {
        setMessage('Invalid email or password. Please enter valid credentials.');
      } else {
        setMessage('Login failed. Please try again.');
      }
      console.error('Login failed:', error.response.data);
    }
  };

  return (
    <div>
      <h2>Login</h2>
      <div className="input-box">
        <label>Email:</label>
        <input type="email" name="email" placeholder="Email" onChange={handleInputChange} />
      </div>
      <div className="input-box">
        <label>Password:</label>
        <input type="password" name="password" placeholder="Password" onChange={handleInputChange} />
      </div>
      <button onClick={handleLogin}>Login</button>
      {message && <p>{message}</p>}
    </div>
  );
};

export default LoginComponent;
