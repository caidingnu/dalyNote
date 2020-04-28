<template>
    <svg :class="svgClass" aria-hidden="true">
        <use :xlink:href="iconName"/>
    </svg>
</template>

<script>
    export default {
        name: "SvgIcon",
        props: {
            iconClass: {
                type: String,
                required: true
            },
            className: {
                type: String,
                default: ""
            }
        },
        computed: {
            iconName() {
                return `#icon-${this.iconClass}`;
            },
            svgClass() {
                if (this.className) {
                    return "svg-icon " + this.className;
                } else {
                    return "svg-icon";
                }
            }
        }
    };
</script>

<style scoped>
    .svg-icon {
        width: 2em;
        height: 2em;
        vertical-align: -0.15em;
        fill: currentColor;
        overflow: hidden;
    }

    .svg-icon:hover {
        cursor: pointer;
        transform: scale(1.5);
        color: red;
    }
</style>