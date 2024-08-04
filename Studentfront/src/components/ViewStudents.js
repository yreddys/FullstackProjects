// src/components/ViewStudents.js

import React, { useState, useEffect } from 'react';
import axios from 'axios';

const ViewStudents = () => {
    const [students, setStudents] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8080/student')
            .then(response => {
                setStudents(response.data);
            })
            .catch(error => {
                console.error('There was an error fetching the students!', error);
            });
    }, []);

    return (
        <div>
            <h2>View Students</h2>
            <ul>
                {students.map(student => (
                    <li key={student.id}>{student.name} - {student.level}</li>
                ))}
            </ul>
        </div>
    );
}

export default ViewStudents;
