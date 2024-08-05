// src/components/AddCourse.js

import React, { useState } from 'react';
import axios from 'axios';

const AddCourse = () => {
    const [photo, setPhoto] = useState(null);
    const [courseName, setCourseName] = useState('');
    const [coursePrice, setCoursePrice] = useState('');
    const [courseDescription, setCourseDescription] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        const formData = new FormData();
        formData.append('photo', photo);
        formData.append('courseName', courseName);
        formData.append('coursePrice', coursePrice);
        formData.append('courseDescription', courseDescription);

        try {
            await axios.post('http://localhost:8080/Course/addCourse', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                },
            });
            alert('Course added successfully');
        } catch (error) {
            console.error('Error adding course:', error);
            alert('Failed to add course');
        }
    };

    return (
        <div style={containerStyle}>
            <h2>Add Course</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Course Name:</label>
                    <input
                        type="text"
                        value={courseName}
                        onChange={(e) => setCourseName(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>Course Price:</label>
                    <input
                        type="number"
                        value={coursePrice}
                        onChange={(e) => setCoursePrice(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>Course Description:</label>
                    <textarea
                        value={courseDescription}
                        onChange={(e) => setCourseDescription(e.target.value)}
                        required
                    ></textarea>
                </div>
                <div>
                    <label>Photo:</label>
                    <input
                        type="file"
                        onChange={(e) => setPhoto(e.target.files[0])}
                        required
                    />
                </div>
                <button type="submit">Add Course</button>
            </form>
        </div>
    );
};

const containerStyle = {
    maxWidth: '600px',
    margin: 'auto',
    padding: '20px',
    border: '1px solid #ddd',
    borderRadius: '4px',
};

export default AddCourse;
