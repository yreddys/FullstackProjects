import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const ViewCourses = () => {
    const [courses, setCourses] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchCourses = async () => {
            try {
                const response = await axios.get('http://localhost:8080/Course/allCourse');
                setCourses(response.data);
            } catch (error) {
                console.error('Error fetching courses:', error);
            }
        };

        fetchCourses();
    }, []);

    const handleEnroll = (course) => {
        navigate('/EnrollForm', { state: { course } });
    };

    return (
        <div style={containerStyle}>
            <h2>Courses</h2>
            {courses.length > 0 ? (
                <ul>
                    {courses.map(course => (
                        <li key={course.id} style={courseItemStyle}>
                            <h3>{course.courseName}</h3>
                            <p>Price: ${course.coursePrice}</p>
                            <p>{course.courseDescription}</p>
                            {course.photoBase64 && (
                                <img
                                    src={`data:image/jpeg;base64,${course.photoBase64}`}
                                    alt={course.courseName}
                                    style={imageStyle}
                                />
                            )}
                            <button onClick={() => handleEnroll(course)}>Enroll</button>
                        </li>
                    ))}
                </ul>
            ) : (
                <p>No courses available</p>
            )}
        </div>
    );
};

const containerStyle = {
    maxWidth: '800px',
    margin: 'auto',
    padding: '20px',
};

const courseItemStyle = {
    borderBottom: '1px solid #ddd',
    padding: '10px 0',
};

const imageStyle = {
    maxWidth: '100%',
    height: 'auto',
};

export default ViewCourses;
