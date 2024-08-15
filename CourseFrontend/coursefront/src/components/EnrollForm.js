import React, { useState } from 'react';
import axios from 'axios';

const EnrollForm = () => {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    mobile: ''
  });

  const [bookingDetails, setBookingDetails] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [confirmationMessage, setConfirmationMessage] = useState('');

  // Handle form input changes
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError('');
    setConfirmationMessage('');

    try {
      const response = await axios.post('http://localhost:8080/booking/book', formData);

      if (response.status === 201) {
        console.log('Booking response:', response.data); // Log response data for debugging
        setBookingDetails(response.data);
        setConfirmationMessage(`Enrolled successfully in the course. Your confirmation ID is: ${response.data.confirmationId}`);
      } else {
        setError('Enrollment failed. Please try again.');
      }
    } catch (error) {
      setError('An error occurred during enrollment.');
      console.error('Enrollment error:', error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="enroll-form">
      {!bookingDetails ? (
        <>
          <h2>Enroll in Course</h2>
          <form onSubmit={handleSubmit}>
            <div className="form-group">
              <label htmlFor="name">Name:</label>
              <input
                type="text"
                id="name"
                name="name"
                value={formData.name}
                onChange={handleChange}
                required
              />
            </div>
            <div className="form-group">
              <label htmlFor="email">Email:</label>
              <input
                type="email"
                id="email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                required
              />
            </div>
            <div className="form-group">
              <label htmlFor="mobile">Mobile:</label>
              <input
                type="text"
                id="mobile"
                name="mobile"
                value={formData.mobile}
                onChange={handleChange}
                required
              />
            </div>
            <button type="submit" disabled={loading}>
              {loading ? 'Submitting...' : 'Enroll'}
            </button>
          </form>
          {error && <p className="error-message">{error}</p>}
        </>
      ) : (
        <div className="booking-confirmation">
          <h2>Booking Confirmation</h2>
          <p>Course Name: {bookingDetails.course ? bookingDetails.course.name : 'Course information not available'}</p>
          <p>Name: {bookingDetails.name}</p>
          <p>Email: {bookingDetails.email}</p>
          <p>Mobile: {bookingDetails.mobile}</p>
          <p>Confirmation ID: {bookingDetails.confirmationId}</p>
        </div>
      )}
      {confirmationMessage && <p className="confirmation-message">{confirmationMessage}</p>}
    </div>
  );
};

export default EnrollForm;
