// src/components/RegistrationComponent.js
import React, { useState } from 'react';
import axios from 'axios';

const RegistrationComponent = () => {
  const [registrationData, setRegistrationData] = useState({
    name: '',
    email: '',
    password: '',
  });

  const [registrationStatus, setRegistrationStatus] = useState({
    message: '',
    error: false,
  });

  const handleInputChange = (e) => {
    setRegistrationData({
      ...registrationData,
      [e.target.name]: e.target.value,
    });
  };

  const handleRegistration = async () => {
    // Check if all fields are filled
    if (!registrationData.name || !registrationData.email || !registrationData.password) {
      setRegistrationStatus({
        message: 'Please fill in all fields.',
        error: true,
      });
      return;
    }

    try {
      const response = await axios.post('http://localhost:8080/save', registrationData);
      if (response.data.includes('already registered')) {
        setRegistrationStatus({
          message: 'Registration failed. This email is already registered.',
          error: true,
        });
      } else {
        setRegistrationStatus({
          message: 'Registration successful. You are registered!',
          error: false,
        });
      }
    } catch (error) {
      console.error('Registration failed:', error.response ? error.response.data : 'Unknown error');
      setRegistrationStatus({
        message: 'Registration failed. Please try again.',
        error: true,
      });
    }
  };

  return (
    <div>
      <h2>Registration</h2>
      <div className="input-box">
        <label>Name:</label>
        <input type="text" name="name" placeholder="Name" onChange={handleInputChange} required />
      </div>

      <div className="input-box">
        <label>Email:</label>
        <input type="email" name="email" placeholder="Email" onChange={handleInputChange} required />
      </div>

      <div className="input-box">
        <label>Password:</label>
        <input type="password" name="password" placeholder="Password" onChange={handleInputChange} required />
      </div>

      <button onClick={handleRegistration}>Register</button>

      {registrationStatus.message && (
        <div style={{ color: registrationStatus.error ? 'red' : 'green', marginTop: '10px' }}>
          {registrationStatus.message}
        </div>
      )}
    </div>
  );
};

export default RegistrationComponent;
